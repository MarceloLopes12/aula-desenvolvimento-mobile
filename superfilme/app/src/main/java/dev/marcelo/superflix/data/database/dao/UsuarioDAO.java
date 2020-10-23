package dev.marcelo.superflix.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import dev.marcelo.superflix.data.model.Usuario;

@Dao
public interface UsuarioDAO {

    @Query("SELECT * FROM usuario where id_usuario = (:idUsuario)")
    Usuario buscarPorId(long idUsuario);

    @Query("SELECT * FROM usuario where usuario = (:usuario)")
    Usuario buscarPorUsuario(String usuario);

    @Insert
    void salvar(Usuario usuario);

    @Delete
    void deletar(Usuario usuario);

    @Update
    void atualizar(Usuario usuario);
}
