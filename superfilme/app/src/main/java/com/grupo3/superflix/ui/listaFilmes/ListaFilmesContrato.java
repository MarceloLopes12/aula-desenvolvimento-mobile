package com.grupo3.superflix.ui.listaFilmes;

import java.util.List;

import com.grupo3.superflix.data.model.Filme;
import com.grupo3.superflix.ui.comum.DestruirPresenter;
import com.grupo3.superflix.ui.comum.ErroView;

public interface ListaFilmesContrato {

    interface ListaFilmesView extends ErroView {
        void mostraFilmes(List<Filme> filmes);
    }

    interface ListaFilmesPresenter extends DestruirPresenter {
        void obtemFilmes();
    }
}
