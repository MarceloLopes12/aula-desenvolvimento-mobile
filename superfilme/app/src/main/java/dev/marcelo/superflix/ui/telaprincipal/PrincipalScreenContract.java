package dev.marcelo.superflix.ui.telaprincipal;

import java.util.List;

import dev.marcelo.superflix.data.model.CategoriaFilme;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.ui.comum.DestruirPresenter;
import dev.marcelo.superflix.ui.comum.ErroView;

public interface PrincipalScreenContract {

    interface PrincipalScreenView extends ErroView {
        void adicionarFilmesACategorias(List<CategoriaFilme> categoriasFilmes);
    }

    interface PricipalScreenPresenter extends DestruirPresenter {
        void obterCategoriasEFilmes();
    }


}
