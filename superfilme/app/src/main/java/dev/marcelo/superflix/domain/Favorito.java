package dev.marcelo.superflix.domain;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.database.AppDataBase;
import dev.marcelo.superflix.data.database.FactoryDataBase;
import dev.marcelo.superflix.data.database.dao.FavoritosDAO;
import dev.marcelo.superflix.data.database.dao.FilmeDAO;
import dev.marcelo.superflix.data.database.dao.UsuarioComFavoritosRelacao;
import dev.marcelo.superflix.data.database.dao.UsuarioFavoritos;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.model.Usuario;

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
