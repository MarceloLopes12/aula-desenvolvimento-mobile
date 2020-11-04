package com.grupo8.superflix.ui.detalhesfilme;

import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.data.model.Usuario;

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
}
