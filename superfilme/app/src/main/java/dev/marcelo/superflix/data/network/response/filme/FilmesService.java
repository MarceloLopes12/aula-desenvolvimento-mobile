package dev.marcelo.superflix.data.network.response.filme;

import dev.marcelo.superflix.data.network.response.filme.FilmesResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmesService {

    @GET("discover/movie")
    Call<FilmesResult> listarFilmesPorCategoria(
                            @Query("api_key") String chaveApi,
                            @Query("language") String language,
                            @Query("with_genres") String categoria,
                            @Query("sort_by") String ordenacao);
}
