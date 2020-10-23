package dev.marcelo.superflix.ui.listaFilmes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.mapper.FilmeMapper;
import dev.marcelo.superflix.data.model.Categoria;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.data.network.ApiService;
import dev.marcelo.superflix.data.network.response.filme.FilmesResult;
import dev.marcelo.superflix.ui.detalhesFilme.DetalhesFilmeActivity;
import dev.marcelo.superflix.ui.listaCategorias.ListaCategoriasActivity;
import dev.marcelo.superflix.ui.listaFavoritos.ListaFavoritosActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaFilmesActivity extends AppCompatActivity
        implements ListaFilmesContrato.ListaFilmesView,
        ListaFilmesAdapter.ItemFilmeClickListener {

    private ListaFilmesAdapter filmesAdapter;
    private ListaFilmesContrato.ListaFilmesPresenter presenter;

    public static final String EXTRA_CATEGORIA = "EXTRA_CATEGORIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_filmes);

        final Categoria categoria = (Categoria) getIntent().getSerializableExtra(EXTRA_CATEGORIA);

        configuraAdapter();

        presenter = new ListaFilmesPresenter(categoria, this);

        presenter.obtemFilmes();
    }

    private void configuraAdapter() {
        final RecyclerView recyclerFilmes = findViewById(R.id.recycler_filmes);

        filmesAdapter = new ListaFilmesAdapter(this);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerFilmes.setLayoutManager(gridLayoutManager);

        recyclerFilmes.setAdapter(filmesAdapter);
    }

    @Override
    public void mostraFilmes(List<Filme> filmes) {
        filmesAdapter.setFilmes(filmes);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(this, "Erro ao obter lista de filmes", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destruirView();
    }

    @Override
    public void onItemFilmeClicado(Filme filme) {
        Intent intent = new Intent( this, DetalhesFilmeActivity.class);
        intent.putExtra(DetalhesFilmeActivity.EXTRA_FILME, filme);
        startActivity(intent);
    }


    public void onFavoritoClicado(View view) {
        Intent intent = new Intent( this, ListaFavoritosActivity.class);
        startActivity(intent);
    }

}