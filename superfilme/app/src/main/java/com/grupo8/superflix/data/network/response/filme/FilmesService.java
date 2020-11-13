package com.grupo8.superflix.data.network.response.filme;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FilmesService {

    @GET("discover/movie")
    Call<FilmesResult> listarFilmesPorCategoria(
                            @Query("api_key") String chaveApi,
                            @Query("language") String language,
                            @Query("with_genres") String categoria,
                            @Query("sort_by") String ordenacao);
    @GET("movie/{Id}")
    Call<FilmeResponse> obterFilme(
            @Path("Id") long id,
            @Query("api_key") String chaveApi,
            @Query("language") String language);
}
