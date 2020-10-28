package com.grupo3.superflix.data.model;

import java.util.List;

public class CategoriaFilme {

    String categoria;
    List<Filme> filmes;

    public CategoriaFilme() {
    }

    public CategoriaFilme(String categoria, List<Filme> filmes) {
        this.categoria = categoria;
        this.filmes = filmes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
