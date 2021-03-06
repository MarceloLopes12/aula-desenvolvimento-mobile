package com.grupo8.superflix.ui.listafilmes;

import java.util.List;

import com.grupo8.superflix.data.mapper.FilmeMapper;
import com.grupo8.superflix.data.model.Categoria;
import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.data.network.ApiService;
import com.grupo8.superflix.data.network.response.filme.FilmesResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFilmesPresenter implements ListaFilmesContrato.ListaFilmesPresenter{

    private ListaFilmesContrato.ListaFilmesView view;

    private Categoria categoria;

    public ListaFilmesPresenter(Categoria categoria, ListaFilmesContrato.ListaFilmesView view) {
        this.categoria = categoria;
        this.view = view;
    }

    @Override
    public void obtemFilmes() {
        ApiService.getFilmeInstance()
                .listarFilmesPorCategoria("dd56ac784462a1c32ad07a07c2a9c494", "pt-BR", categoria.getId().toString(), "popularity.desc")
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()) {
                            final List<Filme> listaFilmes = FilmeMapper
                                    .deResponseParaDominio(response.body()
                                            .getResultadoFilmes());

                            view.mostraFilmes(listaFilmes);
                        }
                        else {
                            view.mostraErro();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesResult> call, Throwable t) {
                        view.mostraErro();
                    }
                });
    }

    @Override
    public void pesquisaFilmes(String titulo) {
        ApiService.getFilmeInstance()
                .listarFilmesPorTitulo("dd56ac784462a1c32ad07a07c2a9c494", "pt-BR", titulo)
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()) {
                            final List<Filme> listaFilmes = FilmeMapper
                                    .deResponseParaDominio(response.body()
                                            .getResultadoFilmes());

                            view.mostraFilmes(listaFilmes);
                        }
                        else {
                            view.mostraErro();
                        }
                    }

                    @Override
                    public void onFailure(Call<FilmesResult> call, Throwable t) {
                        view.mostraErro();
                    }
                });
    }

    @Override
    public void destruirView() {
        this.view = null;
    }
}
