package com.grupo8.superflix.data.network.response.categoria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CategoriasService {

    @GET("genre/movie/list")
    Call<CategoriasResult> listarCategorias(@Query("api_key") String chaveApi, @Query("language") String language);
}
