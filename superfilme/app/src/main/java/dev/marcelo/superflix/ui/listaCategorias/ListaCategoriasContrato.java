package dev.marcelo.superflix.ui.listaCategorias;

import java.util.List;

import dev.marcelo.superflix.data.model.Categoria;

public interface ListaCategoriasContrato {

    interface ListaCategoriasView {

        void mostraCategorias(List<Categoria> categorias);

        void mostraErro();

    }

    interface ListaCategoriasPresenter {

        void obtemCategorias();

        void destruirView();
    }
}
