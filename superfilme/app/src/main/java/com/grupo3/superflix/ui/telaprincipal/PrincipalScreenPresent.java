package com.grupo3.superflix.ui.telaprincipal;

import java.util.ArrayList;
import java.util.List;

import com.grupo3.superflix.data.model.CategoriaFilme;
import com.grupo3.superflix.data.model.Filme;
import com.grupo3.superflix.data.model.Categoria;
import com.grupo3.superflix.data.network.ApiService;
import com.grupo3.superflix.data.network.response.categoria.CategoriasResult;
import com.grupo3.superflix.data.mapper.CategoriaMapper;
import com.grupo3.superflix.data.network.response.filme.FilmesResult;
import com.grupo3.superflix.data.mapper.FilmeMapper;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalScreenPresent implements PrincipalScreenContract.PricipalScreenPresenter {

    private PrincipalScreenContract.PrincipalScreenView view;
    private List<CategoriaFilme> categoriasFilmes = new ArrayList<>();

    public PrincipalScreenPresent(PrincipalScreenContract.PrincipalScreenView view) {
        this.view = view;
    }

    @Override
    public void obterCategoriasEFilmes() {

        obtemCategorias();

    }

    @Override
    public void destruirView() {

    }

    public void obtemCategorias() {

        ApiService.getCategoriaInstance().listarCategorias("dd56ac784462a1c32ad07a07c2a9c494", "pt-BR")
                .enqueue(new Callback<CategoriasResult>() {
                    @Override
                    public void onResponse(Call<CategoriasResult> call, Response<CategoriasResult> response) {
                        if(response.isSuccessful()) {
                            final List<Categoria> listaCategorias = CategoriaMapper
                                    .deResponseParaDominio(response.body().getGenres());

                            for(Categoria c: listaCategorias) {
                                obtemFilmes(c);
                            }
                        }
                        else {

                        }
                    }

                    @Override
                    public void onFailure(Call<CategoriasResult> call, Throwable t) {
                        view.mostraErro();
                    }
                });
    }


    public void obtemFilmes(final Categoria categoria) {
        ApiService.getFilmeInstance()
                .listarFilmesPorCategoria("dd56ac784462a1c32ad07a07c2a9c494", "pt-BR", categoria.getId().toString(), "popularity.desc")
                .enqueue(new Callback<FilmesResult>() {
                    @Override
                    public void onResponse(Call<FilmesResult> call, Response<FilmesResult> response) {
                        if(response.isSuccessful()) {
                            final List<Filme> listaFilmes = FilmeMapper
                                    .deResponseParaDominio(response.body()
                                            .getResultadoFilmes());

                            categoriasFilmes.add(new CategoriaFilme(categoria.getNome() ,listaFilmes));
                            view.adicionarFilmesACategorias(categoriasFilmes);
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



}
