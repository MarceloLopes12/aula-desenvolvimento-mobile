package dev.marcelo.superflix.data.network.response.filme;

import com.squareup.moshi.Json;

public class FilmeResponse {

    @Json(name = "poster_path")
    private final String caminhoPoster;

    @Json(name = "title")
    private final String tituloFilme;

    @Json(name = "overview")
    private final String descricao;

    public FilmeResponse(String caminhoPoster, String tituloFilme, String descricao) {
        this.caminhoPoster = caminhoPoster;
        this.tituloFilme = tituloFilme;
        this.descricao = descricao;
    }

    public final String getCaminhoPoster() {
        return caminhoPoster;
    }

    public final String getTituloFilme() {
        return tituloFilme;
    }

    public final String getDescricao(){ return descricao; }
}
