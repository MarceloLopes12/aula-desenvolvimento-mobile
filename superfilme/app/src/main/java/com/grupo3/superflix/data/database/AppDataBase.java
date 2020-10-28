package com.grupo3.superflix.data.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.grupo3.superflix.data.model.Filme;
import com.grupo3.superflix.data.model.Usuario;
import com.grupo3.superflix.data.database.dao.*;

@Database(entities = {Usuario.class, Filme.class, UsuarioFavoritos.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract FilmeDAO getFilmeDAO();
    public abstract FavoritosDAO getFavoritosDAO();
}