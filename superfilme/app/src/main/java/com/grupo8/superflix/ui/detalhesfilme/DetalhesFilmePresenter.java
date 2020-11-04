package com.grupo8.superflix.ui.detalhesfilme;

import android.content.Context;

import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.data.model.Usuario;
import com.grupo8.superflix.domain.Favorito;

public class DetalhesFilmePresenter implements DetalhesFilmeContrato.DetalhesFilmePresenter {

    private DetalhesFilmeContrato.DetalhesFilmeView view;
    private Filme filme;

    public DetalhesFilmePresenter(Filme filme, DetalhesFilmeContrato.DetalhesFilmeView view) {
        this.filme = filme;
        this.view = view;
    }

    @Override
    public void obtemFilme() {
        view.mostraFilme(new Filme(filme.getTitulo(), filme.getCaminhoPoster(), filme.getDescricao()));
    }

    @Override
    public void adicionarFilmeFavoritos(final Filme filme, final Context context) {
        new Thread() {
            @Override
            public void run() {
                Favorito favorito = new Favorito(context);
                favorito.adicionar(filme);
            }
        }.start();
    }
}
