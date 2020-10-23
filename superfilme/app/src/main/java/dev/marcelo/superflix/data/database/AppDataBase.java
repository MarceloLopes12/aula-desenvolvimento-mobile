package dev.marcelo.superflix.data.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.model.Usuario;
import dev.marcelo.superflix.data.database.dao.*;

@Database(entities = {Usuario.class, Filme.class, UsuarioFavoritos.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract FilmeDAO getFilmeDAO();
    public abstract FavoritosDAO getFavoritosDAO();
}