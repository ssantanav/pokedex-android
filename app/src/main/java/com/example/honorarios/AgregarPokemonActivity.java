package com.example.honorarios;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgregarPokemonActivity extends AppCompatActivity {

    private EditText editTextName, editTextType;
    private Button buttonSave;
    private BaseDatos basePokemon;
    private int pokemonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_pokemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextNumero = findViewById(R.id.editTextNumero);
        EditText editTextNombre = findViewById(R.id.editTextNombre);
        EditText editTextTipo = findViewById(R.id.editTextTipo);
        Button buttonSave = findViewById(R.id.buttonGuardar);
        BaseDatos basePokemon = new BaseDatos(this);

        // Verificar si se está editando un Pokémon existente
        if (getIntent().hasExtra("pokemonId")) {
            pokemonId = getIntent().getIntExtra("pokemonId", -1);
            editTextNumero.setText(getIntent().getStringExtra("pokemonNumero"));
            editTextNombre.setText(getIntent().getStringExtra("pokemonNombre"));
            editTextTipo.setText(getIntent().getStringExtra("pokemonTipo"));
        }
        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String numero = editTextNumero.getText().toString();
                String nombre = editTextNombre.getText().toString();
                String tipo = editTextTipo.getText().toString();

                if (pokemonId == -1) {
                    // Agregar un nuevo Pokémon
                    basePokemon.agregarPokemon(Integer.parseInt(numero), nombre, tipo);
                    Toast.makeText(AgregarPokemonActivity.this, "Pokémon agregado", Toast.LENGTH_SHORT).show();
                } else {
                    // Actualizar Pokémon existente
                    basePokemon.actualizarPokemon(pokemonId, Integer.parseInt(numero),  nombre , tipo);
                    Toast.makeText(AgregarPokemonActivity.this, "Pokémon actualizado", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}