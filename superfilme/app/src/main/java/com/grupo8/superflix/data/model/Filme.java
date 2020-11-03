package com.grupo8.superflix.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Filme implements Serializable {

    @ColumnInfo(name = "id_filme")
    @PrimaryKey(autoGenerate = true)
    private long uid;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "caminho_poster")
    private String caminhoPoster;

    @ColumnInfo(name = "descricao")
    private String descricao;

    public Filme(String titulo, String caminhoPoster, String descricao) {
        this.titulo = titulo;
        this.caminhoPoster = caminhoPoster;
        this.descricao = descricao;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCaminhoPoster() {
        return caminhoPoster;
    }

    public void setCaminhoPoster(String caminhoPoster) {
        this.caminhoPoster = caminhoPoster;
    }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public boolean equals(Object o) {
        Filme f = (Filme) o;

        return titulo.equals(f.titulo);
    }

    @Override
    public int hashCode() {
        return (int) uid;
    }
}
