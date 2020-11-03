package com.grupo8.superflix.data.network.response.categoria;

import com.squareup.moshi.Json;

public class CategoriaResponse {

    @Json(name = "name")
    private final String nome;

    @Json(name = "id")
    private final String id;

    public CategoriaResponse(String name, String id) {
        this.id = id;
        this.nome = name;
    }

    public String getNomeCategoria() {
        return nome;
    }


    public String getIdCategoria() {
        return id;
    }
}
