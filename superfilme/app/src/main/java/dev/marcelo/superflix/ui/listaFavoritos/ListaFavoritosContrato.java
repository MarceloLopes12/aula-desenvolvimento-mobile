package dev.marcelo.superflix.ui.listaFavoritos;

import android.content.Context;

import java.util.List;

import dev.marcelo.superflix.data.model.Filme;

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
