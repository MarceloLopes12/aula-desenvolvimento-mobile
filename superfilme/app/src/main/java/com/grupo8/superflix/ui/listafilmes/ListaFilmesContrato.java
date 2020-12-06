package com.grupo8.superflix.ui.listafilmes;

import java.util.List;

import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.ui.comum.DestruirPresenter;
import com.grupo8.superflix.ui.comum.ErroView;

public interface ListaFilmesContrato {

    interface ListaFilmesView extends ErroView {
        void mostraFilmes(List<Filme> filmes);
    }

    interface ListaFilmesPresenter extends DestruirPresenter {
        void obtemFilmes();

        void pesquisaFilmes(String titulo);
    }
}
