package dev.marcelo.superflix.ui.listaFilmes;

import java.util.List;

import dev.marcelo.superflix.data.mapper.FilmeMapper;
import dev.marcelo.superflix.data.model.Categoria;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.network.ApiService;
import dev.marcelo.superflix.data.network.response.filme.FilmesResult;
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
    public void destruirView() {
        this.view = null;
    }
}
