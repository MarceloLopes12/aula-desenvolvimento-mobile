package com.grupo8.superflix.ui.categorias;

import java.util.List;

import com.grupo8.superflix.data.model.Categoria;

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
