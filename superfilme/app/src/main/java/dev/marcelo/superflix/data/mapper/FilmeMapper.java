package dev.marcelo.superflix.data.mapper;

import java.util.ArrayList;
import java.util.List;

import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.network.response.filme.FilmeResponse;

public class FilmeMapper {
    public static List<Filme> deResponseParaDominio(List<FilmeResponse> listaFilmeResponse) {
        List<Filme> listaFilmes = new ArrayList<>();

        for(FilmeResponse filmeResponse : listaFilmeResponse) {
            final Filme filme = new Filme(filmeResponse.getTituloFilme(), filmeResponse.getCaminhoPoster(), filmeResponse.getDescricao());
            listaFilmes.add(filme);
        }
        return listaFilmes;
    }
}
