package com.grupo8.superflix.data.database.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"id_usuario","id"})
public class UsuarioFavoritos {

    @ColumnInfo(name = "id_usuario")
    public long idUsuario;

    @ColumnInfo(name = "id")
    public long idFilme;

    public UsuarioFavoritos(long idUsuario, long idFilme) {
        this.idUsuario = idUsuario;
        this.idFilme = idFilme;
    }
}
