package com.grupo8.superflix.data.network.response.filme;

import com.squareup.moshi.Json;

public class FilmeResponse {

    @Json(name = "poster_path")
    private final String caminhoPoster;

    @Json(name = "title")
    private final String tituloFilme;

    @Json(name = "overview")
    private final String descricao;

    @Json(name = "id")
    private final long id;

    public FilmeResponse(String caminhoPoster, String tituloFilme, String descricao, long id) {
        this.caminhoPoster = caminhoPoster;
        this.tituloFilme = tituloFilme;
        this.descricao = descricao;
        this.id = id;
    }

    public final String getCaminhoPoster() {
        return caminhoPoster;
    }

    public final String getTituloFilme() {
        return tituloFilme;
    }

    public final String getDescricao(){ return descricao; }

    public final long getId(){ return id; }

}
