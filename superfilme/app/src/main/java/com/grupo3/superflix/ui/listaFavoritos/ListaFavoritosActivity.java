package com.grupo3.superflix.ui.listaFavoritos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.grupo3.superflix.R;
import com.grupo3.superflix.data.model.Filme;
import com.grupo3.superflix.ui.detalhesFilme.DetalhesFilmeActivity;
import com.grupo3.superflix.data.model.Usuario;
import com.grupo3.superflix.ui.listaFilmes.ListaFilmesAdapter;
import com.grupo3.superflix.ui.principal.MenuPrincipal;

public class ListaFavoritosActivity extends AppCompatActivity
        implements ListaFavoritosContrato.ListaFavoritosView,
        ListaFilmesAdapter.ItemFilmeClickListener {

    private Usuario usuario;
    private ListaFilmesAdapter filmesAdapter;
    private ListaFavoritosContrato.ListaFavoritosPresenter presenter;

    public static final String EXTRA_USUARIO = "EXTRA_USUARIO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_favoritos);

        usuario = (Usuario) getIntent().getSerializableExtra(EXTRA_USUARIO);

        configuraAdapter();

        MenuPrincipal.configurarMenu(getSupportFragmentManager().beginTransaction(),
                R.id.fragment_menu_favoritos, R.id.btn_favoritos);

        presenter = new ListaFavoritosPresenter(usuario, this);

        presenter.obtemFavoritos(getApplicationContext());
    }

    private void configuraAdapter() {
        final RecyclerView recyclerFilmes = findViewById(R.id.favoritos_lista);

        filmesAdapter = new ListaFilmesAdapter(this);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerFilmes.setLayoutManager(gridLayoutManager);

        recyclerFilmes.setAdapter(filmesAdapter);
    }

    @Override
    public void mostraFavoritos(List<Filme> favoritos) {
        filmesAdapter.setFilmes(favoritos);
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