package dev.marcelo.superflix.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Filme implements Serializable {

    @ColumnInfo(name = "id_filme")
    @PrimaryKey(autoGenerate = true)
    private long uid;

    @ColumnInfo(name = "titulo")
    private String titulo;

    @ColumnInfo(name = "caminho_poster")
    private String caminhoPoster;

    public Filme(String titulo, String caminhoPoster) {
        this.titulo = titulo;
        this.caminhoPoster = caminhoPoster;
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
