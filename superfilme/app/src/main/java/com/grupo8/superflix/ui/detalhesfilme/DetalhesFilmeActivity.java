package com.grupo8.superflix.ui.detalhesfilme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

public class DetalhesFilmeActivity extends AppCompatActivity
                implements DetalhesFilmeContrato.DetalhesFilmeView {

    public static final String EXTRA_FILME = "EXTRA_FILME";

    private long idFilme;
    private Filme filme;

    private DetalhesFilmeContrato.DetalhesFilmePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);


        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String id = uri.getQueryParameter("id");
            idFilme = Long.parseLong(id);
        } else {
            if ("application/json".equals(intent.getType())) {
                try {
                    Log.d("intent", intent.getExtras().toString());
                    for (String k : intent.getExtras().keySet()) {
                        Log.d("intent", k);
                    }
                    JSONObject json = new JSONObject(intent.getExtras().getString("json"));
                    Log.e("intent", json.toString());
                    idFilme = json.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                idFilme = (long) getIntent().getSerializableExtra(EXTRA_FILME);
            }
        }

        presenter = new DetalhesFilmePresenter(idFilme, this);
        presenter.obtemFilme();
    }

    public void adicionarFavorrito(View view) {
        presenter.adicionarFilmeFavoritos(filme, this);
    }

    public void onClickShareFilme(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://superflix.edu.br?id="+filme.getId());
        sendIntent.setType("text/plain");

        if(sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendIntent);
        } else {
            Toast.makeText(getApplicationContext(), "falhou", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void mostraFilme(Filme filme) {
        this.filme = filme;
        TextView textTituloFilme = findViewById(R.id.text_titulo_filme);
        ImageView imagePosterFilme = findViewById(R.id.image_poster_filme);
        TextView textDescricaoFilme = findViewById(R.id.text_descricao_filme);

        textDescricaoFilme.setText(filme.getDescricao());
        textTituloFilme.setText(filme.getTitulo());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.getCaminhoPoster())
                .into(imagePosterFilme);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(this, "Erro ao obter lista de filmes", Toast.LENGTH_LONG).show();
    }
}