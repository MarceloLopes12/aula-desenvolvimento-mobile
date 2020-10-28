package com.grupo3.superflix.ui.listaFavoritos;

import android.content.Context;

import java.util.List;

import com.grupo3.superflix.data.model.Filme;

public interface ListaFavoritosContrato {

    interface ListaFavoritosView {
        void mostraFavoritos(List<Filme> favoritos);
        void mostraErro();
    }

    interface ListaFavoritosPresenter {

        void obtemFavoritos(Context context);

        void destruirView();
    }
}
