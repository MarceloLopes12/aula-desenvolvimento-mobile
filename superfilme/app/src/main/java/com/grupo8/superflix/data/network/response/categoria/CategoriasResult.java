package com.grupo8.superflix.data.network.response.categoria;

import com.squareup.moshi.Json;

import java.util.List;

public class CategoriasResult {

    @Json(name = "genres")
    private final List<CategoriaResponse> genres;

    public CategoriasResult(List<CategoriaResponse> genres) {
        this.genres = genres;
    }

    public List<CategoriaResponse> getGenres() {
        return genres;
    }

}
