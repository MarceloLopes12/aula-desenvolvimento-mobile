package dev.marcelo.superflix.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.marcelo.superflix.data.model.Filme;


@Dao
public interface FilmeDAO {

    @Query("SELECT * FROM filme where id_filme = (:idFilme)")
    Filme buscarPorId(long idFilme);

    @Query("SELECT * FROM filme where titulo = (:titulo)")
    Filme buscarPorTitulo(String titulo);

    @Query("SELECT * FROM filme")
    List<Filme> buscarPorTodos();


    @Insert
    void salvar(Filme filme);

    @Delete
    void deletar(Filme filme);

    @Update
    void atualizar(Filme filme);

}
