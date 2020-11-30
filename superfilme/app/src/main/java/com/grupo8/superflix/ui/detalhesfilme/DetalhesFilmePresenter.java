package com.grupo8.superflix.ui.detalhesfilme;

import android.content.Context;
import android.content.Intent;

import com.grupo8.superflix.data.mapper.FilmeMapper;
import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.data.network.ApiService;
import com.grupo8.superflix.data.network.response.filme.FilmeResponse;
import com.grupo8.superflix.data.network.response.filme.FilmesResult;
import com.grupo8.superflix.domain.Favorito;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesFilmePresenter implements DetalhesFilmeContrato.DetalhesFilmePresenter {

    private DetalhesFilmeContrato.DetalhesFilmeView view;
    private long idFilme;

    public  DetalhesFilmePresenter(Long idFilme, DetalhesFilmeContrato.DetalhesFilmeView view) {
        this.idFilme = idFilme;
        this.view = view;
    }

    @Override
    public void obtemFilme() {
        ApiService.getFilmeInstance()
                .obterFilme(idFilme, "dd56ac784462a1c32ad07a07c2a9c494", "pt-BR")
                .enqueue(new Callback<FilmeResponse>() {
                    @Override
                    public void onResponse(Call<FilmeResponse> call, Response<FilmeResponse> response) {
                        final Filme filmes = FilmeMapper
                                .deResponseParaDominio(response.body());

                        view.mostraFilme(filmes);
                    }

                    @Override
                    public void onFailure(Call<FilmeResponse> call, Throwable t) {
                        view.mostraErro();
                    }
                });

    }

    @Override
    public void removerFilmeFavoritos(final Filme filme, final Context context) {
        new Thread() {
            @Override
            public void run() {
                Favorito favorito = new Favorito(context);
                favorito.remover(filme);
            }
        }.start();
}

    @Override
    public void adicionarFilmeFavoritos(final Filme filme, final Context context) {
        new Thread() {
            @Override
            public void run() {
                Favorito favorito = new Favorito(context);
                favorito.adicionar(filme);
            }
        }.start();
    }

}
