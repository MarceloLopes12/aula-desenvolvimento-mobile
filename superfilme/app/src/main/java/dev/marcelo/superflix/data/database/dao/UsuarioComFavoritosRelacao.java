package dev.marcelo.superflix.data.database.dao;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.model.Usuario;

public class UsuarioComFavoritosRelacao {
    @Embedded
    public Usuario usuario;

    @Relation(
            parentColumn = "id_usuario",
            entityColumn = "id_filme",
            associateBy = @Junction(UsuarioFavoritos.class)
    )
    public List<Filme> favoritos;

}
