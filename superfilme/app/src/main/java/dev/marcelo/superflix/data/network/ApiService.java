package dev.marcelo.superflix.data.network;

import dev.marcelo.superflix.data.network.response.categoria.CategoriasService;
import dev.marcelo.superflix.data.network.response.filme.FilmesService;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ApiService {

    private static CategoriasService CATEGORIA_INSTANCE;
    private static FilmesService FILME_INSTANCE;

    public static CategoriasService getCategoriaInstance() {
        if (CATEGORIA_INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();

            CATEGORIA_INSTANCE = retrofit.create(CategoriasService.class);
        }

        return CATEGORIA_INSTANCE;
    }

    public static FilmesService getFilmeInstance() {
        if (FILME_INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();

            FILME_INSTANCE = retrofit.create(FilmesService.class);
        }

        return FILME_INSTANCE;
    }
}
