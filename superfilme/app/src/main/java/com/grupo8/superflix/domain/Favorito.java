package com.grupo8.superflix.domain;

import android.content.Context;

import java.util.List;

import com.grupo8.superflix.data.database.AppDataBase;
import com.grupo8.superflix.data.database.FactoryDataBase;
import com.grupo8.superflix.data.database.dao.FilmeDAO;
import com.grupo8.superflix.data.model.Filme;

public class Favorito {

    private AppDataBase appDataBase;

    public Favorito(Context context) {

        appDataBase = FactoryDataBase.getInstanceAppDataBase(context);

    }

    public void adicionar(Filme filme) {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        List<Filme> favoritos = filmeDAO.buscarPorTodos();

        if(!favoritos.contains(filme)) filmeDAO.salvar(filme);
    }

    public void remover(Filme filme) {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        List<Filme> favoritos = filmeDAO.buscarPorTodos();

        if(favoritos.contains(filme)) filmeDAO.deletar(filme);
    }

    public List<Filme> listar() {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        return filmeDAO.buscarPorTodos();
    }

    private Filme salvar(Filme filme) {
        FilmeDAO filmeDAO = appDataBase.getFilmeDAO();

        if(filmeDAO.buscarPorTitulo(filme.getTitulo()) == null) {
            filmeDAO.salvar(filme);
        }

        return filmeDAO.buscarPorTitulo(filme.getTitulo());
    }

}
