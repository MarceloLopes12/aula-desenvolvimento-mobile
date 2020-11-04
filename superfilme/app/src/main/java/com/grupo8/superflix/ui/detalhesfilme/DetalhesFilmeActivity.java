package com.grupo8.superflix.ui.detalhesfilme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.grupo8.superflix.data.model.Categoria;
import com.grupo8.superflix.ui.categorias.ListaCategoriasContrato;
import com.grupo8.superflix.ui.categorias.ListaCategoriasPresenter;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesContrato;
import com.squareup.picasso.Picasso;

import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.domain.Favorito;

public class DetalhesFilmeActivity extends AppCompatActivity
                implements DetalhesFilmeContrato.DetalhesFilmeView {

    public static final String EXTRA_FILME = "EXTRA_FILME";

    private Filme filme;

    private DetalhesFilmeContrato.DetalhesFilmePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);

        presenter = new DetalhesFilmePresenter(filme, this);
        presenter.obtemFilme();
    }

    public void adicionarFavorrito(View view) {
        presenter.adicionarFilmeFavoritos(filme, this);
    }


    @Override
    public void mostraFilme(Filme filme) {
        TextView textTituloFilme = findViewById(R.id.text_titulo_filme);
        ImageView imagePosterFilme = findViewById(R.id.image_poster_filme);
        TextView textDescricaoFilme = findViewById(R.id.text_descricao_filme);

        filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);
        textDescricaoFilme.setText(filme.getDescricao());
        textTituloFilme.setText(filme.getTitulo());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.getCaminhoPoster())
                .into(imagePosterFilme);
    }
}