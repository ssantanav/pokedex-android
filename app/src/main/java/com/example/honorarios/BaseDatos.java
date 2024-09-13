package com.example.honorarios;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDatos extends SQLiteOpenHelper{
    private static final String NOMBREBASE = "pokemon.db";
    private static final  int VERSION = 1;

    public BaseDatos(@Nullable Context context){
        super(context, NOMBREBASE, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE pokemon(" +
                "id int PRIMARY KEY AUTOINCREMENT," +
                "numero int," +
                "nombre varchar(30), " +
                "tipo varchar(20) "+
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarPokemon(int numero, String nombre, String tipo){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO pokemom(numero, nombre, tipo) " +
                        "VALUES (" +numero+ ",'" + nombre + "','" + tipo + "')";
        db.execSQL(sql);
    }

    public void eliminarPokemon(int id){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DELETE FROM pokemon WHERE id = " + id;
        db.execSQL(sql);
    }

    public void actualizarPokemon(int id, int numero , String nombre, String tipo){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE pokemon SET " +
                "numero = " + numero + ","+
                "nombre = '" + nombre  + "'," +
                "tipo = '" + tipo + "'" +
                "WHERE id = " + id;
        db.execSQL(sql);
    }

    public Cursor obtenerListadoPokemon(){
        SQLiteDatabase db =  this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM pokemon", null);
    }

}
