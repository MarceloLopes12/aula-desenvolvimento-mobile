package dev.marcelo.superflix.ui.listaFilmes;

import java.util.List;

import dev.marcelo.superflix.data.model.Filme;

public interface ListaFilmesContrato {

    interface ListaFilmesView {
        void mostraFilmes(List<Filme> filmes);
        void mostraErro();
    }

    interface ListaFilmesPresenter {

        void obtemFilmes();

        void destruirView();
    }
}
