package com.grupo8.superflix.ui.detalhesfilme;

import com.grupo8.superflix.data.model.Filme;

import java.util.List;

public interface DetalhesFilmeContrato {


    interface DetalhesFilmeView {
        void mostraFilme(Filme filme);
    }

    interface DetalhesFilmePresenter {
        void obtemFilme();
    }
}
