package com.grupo3.superflix.data.mapper;

import java.util.ArrayList;
import java.util.List;

import com.grupo3.superflix.data.model.Filme;
import com.grupo3.superflix.data.network.response.filme.FilmeResponse;

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