package dev.marcelo.superflix.ui.listaFavoritos;

import android.content.Context;

import java.util.List;

import dev.marcelo.superflix.data.database.AppDataBase;
import dev.marcelo.superflix.data.database.FactoryDataBase;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.model.Usuario;
import dev.marcelo.superflix.data.network.ApiService;
import dev.marcelo.superflix.domain.Favorito;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
