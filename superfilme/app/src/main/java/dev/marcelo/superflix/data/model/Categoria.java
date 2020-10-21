package dev.marcelo.superflix.data.model;

import java.io.Serializable;

public class Categoria implements Serializable {

    private final String nome;
    private final String id;

    public Categoria(String nome, String id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }
}
