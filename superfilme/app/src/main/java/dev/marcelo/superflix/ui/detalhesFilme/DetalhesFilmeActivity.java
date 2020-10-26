package dev.marcelo.superflix.ui.detalhesFilme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.domain.Favorito;
import dev.marcelo.superflix.ui.listaCategorias.ListaCategoriasAdapter;
import dev.marcelo.superflix.ui.listaCategorias.ListaCategoriasContrato;
import dev.marcelo.superflix.ui.listaFilmes.ListaFilmesAdapter;
import dev.marcelo.superflix.view.FilmeFragment;

public class DetalhesFilmeActivity extends AppCompatActivity {

    public static final String EXTRA_FILME = "EXTRA_FILME";

    private Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

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

    public void adicionarFavorrito(View view) {
        new Thread() {
            @Override
            public void run() {
                Favorito favorito = new Favorito(getApplicationContext());
                favorito.adicionar(filme);
            }
        }.start();
    }


}