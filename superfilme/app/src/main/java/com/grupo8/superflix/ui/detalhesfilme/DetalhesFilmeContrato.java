package com.grupo8.superflix.ui.detalhesfilme;

import android.content.Context;

import com.grupo8.superflix.data.model.Filme;


public interface DetalhesFilmeContrato {


    interface DetalhesFilmeView {
        void mostraFilme(Filme filme);

        void mostraErro();
    }

    interface DetalhesFilmePresenter {
        void obtemFilme();

        void removerFilmeFavoritos(Filme filme, Context context);

        void adicionarFilmeFavoritos(Filme filme, Context context);
    }
}
