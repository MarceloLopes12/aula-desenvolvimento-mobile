package com.grupo3.superflix.ui.telaprincipal;

import java.util.List;

import com.grupo3.superflix.data.model.CategoriaFilme;
import com.grupo3.superflix.ui.comum.DestruirPresenter;
import com.grupo3.superflix.ui.comum.ErroView;

public interface PrincipalScreenContract {

    interface PrincipalScreenView extends ErroView {
        void adicionarFilmesACategorias(List<CategoriaFilme> categoriasFilmes);
    }

    interface PricipalScreenPresenter extends DestruirPresenter {
        void obterCategoriasEFilmes();
    }


}
