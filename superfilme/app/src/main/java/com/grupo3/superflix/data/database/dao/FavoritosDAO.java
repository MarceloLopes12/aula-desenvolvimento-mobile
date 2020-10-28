package com.grupo3.superflix.data.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface FavoritosDAO {

    @Transaction
    @Query("select * from usuario where id_usuario = (:idUsuario)")
    UsuarioComFavoritosRelacao getFavoritosUsuario(long idUsuario);

    @Insert
    void salvar(UsuarioFavoritos usuarioFavoritos);

}
