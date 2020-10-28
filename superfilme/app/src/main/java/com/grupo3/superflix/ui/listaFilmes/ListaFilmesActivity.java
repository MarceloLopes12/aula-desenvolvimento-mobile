package com.grupo3.superflix.ui.listaFilmes;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import com.grupo3.superflix.R;
import com.grupo3.superflix.data.model.Categoria;
import com.grupo3.superflix.data.model.Filme;
import com.grupo3.superflix.ui.detalhesFilme.DetalhesFilmeActivity;
import com.grupo3.superflix.ui.principal.MenuPrincipal;

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

        MenuPrincipal.configurarMenu(getSupportFragmentManager().beginTransaction(),
                R.id.fragment_menu_lista_filme, R.id.btn_categrias);

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

}