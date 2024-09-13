package com.example.honorarios;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private BaseDatos basePokemon;
    private ListView listViewPokemon;
    private Button buttonAgregarPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        basePokemon = new BaseDatos(this);
        listViewPokemon = findViewById(R.id.listViewPokemon);
        buttonAgregarPokemon = findViewById(R.id.buttonAddPokemon);

        cargarListaPokemon();

        buttonAgregarPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarPokemonActivity.class);
                startActivity(intent);
            }
        });

        listViewPokemon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listViewPokemon.getItemAtPosition(position);
                int pokemonId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String pokemonNumero = cursor.getString(cursor.getColumnIndexOrThrow("numero"));
                String pokemonNombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String pokemonTipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"));

                Intent intent = new Intent(MainActivity.this, AgregarPokemonActivity.class);
                intent.putExtra("pokemonId", pokemonId);
                intent.putExtra("pokemonNumero", pokemonNumero);
                intent.putExtra("pokemonNombre", pokemonNombre);
                intent.putExtra("pokemonTipo", pokemonTipo);
                startActivity(intent);
            }
        });
    }

    private void cargarListaPokemon() {
        Cursor cursor = basePokemon.obtenerListadoPokemon();
        String[] from = {"numero","nombre", "tipo"};
        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2,
                cursor,
                from,
                to,
                0
        );
        listViewPokemon.setAdapter(adapter);
    }
}