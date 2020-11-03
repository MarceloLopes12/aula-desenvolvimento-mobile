package com.grupo8.superflix.ui.detalhesfilme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grupo8.superflix.ui.principal.MenuPrincipal;
import com.squareup.picasso.Picasso;

import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.domain.Favorito;

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

        MenuPrincipal.configurarMenu(getSupportFragmentManager().beginTransaction(),
                R.id.fragment_menu_detalhe_filme, R.id.btn_categrias);

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