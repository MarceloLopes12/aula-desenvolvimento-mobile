package com.grupo8.superflix.ui.inicio;

import java.util.List;

import com.grupo8.superflix.data.model.CategoriaFilme;
import com.grupo8.superflix.ui.comum.DestruirPresenter;
import com.grupo8.superflix.ui.comum.ErroView;

public interface PrincipalScreenContract {

    interface PrincipalScreenView extends ErroView {
        void adicionarFilmesACategorias(List<CategoriaFilme> categoriasFilmes);
    }

    interface PricipalScreenPresenter extends DestruirPresenter {
        void obterCategoriasEFilmes();
    }


}
