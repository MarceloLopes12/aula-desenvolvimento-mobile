package dev.marcelo.superflix.ui.listaFilmes;

import java.util.List;

import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.ui.comum.DestruirPresenter;
import dev.marcelo.superflix.ui.comum.ErroView;

public interface ListaFilmesContrato {

    interface ListaFilmesView extends ErroView {
        void mostraFilmes(List<Filme> filmes);
    }

    interface ListaFilmesPresenter extends DestruirPresenter {
        void obtemFilmes();
    }
}
