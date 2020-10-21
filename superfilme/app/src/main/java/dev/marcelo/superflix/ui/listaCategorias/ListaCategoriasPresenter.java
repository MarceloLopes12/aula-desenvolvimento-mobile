package dev.marcelo.superflix.ui.listaCategorias;

import java.util.List;

import dev.marcelo.superflix.data.mapper.CategoriaMapper;
import dev.marcelo.superflix.data.model.Categoria;
import dev.marcelo.superflix.data.network.ApiService;
import dev.marcelo.superflix.data.network.response.categoria.CategoriasResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaCategoriasPresenter implements ListaCategoriasContrato.ListaCategoriasPresenter{

    private ListaCategoriasContrato.ListaCategoriasView view;

    public ListaCategoriasPresenter(ListaCategoriasContrato.ListaCategoriasView view) {
        this.view = view;
    }

    @Override
    public void obtemCategorias() {
        ApiService.getCategoriaInstance().listarCategorias("dd56ac784462a1c32ad07a07c2a9c494", "pt-BR")
                .enqueue(new Callback<CategoriasResult>() {
                    @Override
                    public void onResponse(Call<CategoriasResult> call, Response<CategoriasResult> response) {
                        if(response.isSuccessful()) {
                            final List<Categoria> listaCategorias = CategoriaMapper
                                    .deResponseParaDominio(response.body().getGenres());

                            view.mostraCategorias(listaCategorias);
                        }
                        else {
                            view.mostraErro();
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoriasResult> call, Throwable t) {
                        view.mostraErro();
                    }
                });
    }

    @Override
    public void destruirView() {
        this.view = null;
    }
}
