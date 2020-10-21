package dev.marcelo.superflix.data.network.response.filme;

import com.squareup.moshi.Json;

public class FilmeResponse {

    @Json(name = "poster_path")
    private final String caminhoPoster;

    @Json(name = "title")
    private final String tituloFilme;

    public FilmeResponse(String caminhoPoster, String tituloFilme) {
        this.caminhoPoster = caminhoPoster;
        this.tituloFilme = tituloFilme;
    }

    public final String getCaminhoPoster() {
        return caminhoPoster;
    }

    public final String getTituloFilme() {
        return tituloFilme;
    }
}
