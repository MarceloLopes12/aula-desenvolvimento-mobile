package dev.marcelo.superflix.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Filme implements Parcelable {

    public String nome;
    public String descricao;
    public String urlCapa;
    public int ano;
    public int id;

    public Filme(String nome, String descricao, String urlCapa, int ano, int id) {
        this.nome = nome;
        this.descricao = descricao;
        this.urlCapa = urlCapa;
        this.ano = ano;
        this.id = id;
    }

    private Filme(Parcel p) {
        this.nome = p.readString();
        this.descricao = p.readString();
        this.urlCapa = p.readString();
        this.ano = p.readInt();
        this.id = p.readInt();
    }

    public static  final Parcelable.Creator<Filme>
            CREATOR = new Parcelable.Creator<Filme>() {

        public Filme createFromParcel(Parcel in) { return new Filme(in); }

        public Filme[] newArray(int size) { return new  Filme[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.nome);
        parcel.writeString(this.descricao);
        parcel.writeString(this.urlCapa);
        parcel.writeInt(this.ano);
        parcel.writeInt(this.id);
    }
}
