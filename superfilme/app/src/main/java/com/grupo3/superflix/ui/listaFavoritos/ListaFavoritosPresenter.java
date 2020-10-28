package com.grupo3.superflix.ui.listaFavoritos;

import android.content.Context;

import com.grupo3.superflix.data.model.Usuario;
import com.grupo3.superflix.domain.Favorito;

public class ListaFavoritosPresenter implements ListaFavoritosContrato.ListaFavoritosPresenter {

    private ListaFavoritosContrato.ListaFavoritosView view;
    private Usuario usuario;

    public ListaFavoritosPresenter(Usuario usuario, ListaFavoritosContrato.ListaFavoritosView view) {
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
