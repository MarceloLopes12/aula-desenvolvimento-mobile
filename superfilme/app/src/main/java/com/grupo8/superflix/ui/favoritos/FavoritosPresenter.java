package com.grupo8.superflix.ui.favoritos;

import android.content.Context;

import com.grupo8.superflix.data.model.Usuario;
import com.grupo8.superflix.domain.Favorito;

public class FavoritosPresenter implements FavoritosContrato.ListaFavoritosPresenter {

    private FavoritosContrato.ListaFavoritosView view;
    private Usuario usuario;

    public FavoritosPresenter(Usuario usuario, FavoritosContrato.ListaFavoritosView view) {
        this.usuario = usuario;
        this.view = view;
    }
    
    @Override
    public void obtemFavoritos(final Context context) {

        new Thread() {
            @Override
            public void run() {

                view.mostraFavoritos(new Favorito(context).listar());

            }
        }.start();

    }

    @Override
    public void destruirView() {
        this.view = null;
    }
}
