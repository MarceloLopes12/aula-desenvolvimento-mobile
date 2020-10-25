package dev.marcelo.superflix.ui.telaprincipal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.model.CategoriaFilme;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.ui.detalhesFilme.DetalhesFilmeActivity;
import dev.marcelo.superflix.ui.listaFilmes.ListaFilmesAdapter;

public class TelaPrincipal extends AppCompatActivity implements PrincipalScreenContract.PrincipalScreenView {

    private Button btnSearch;
    private CarrosselCategoriaAdapter carrosselCategoriaAdapter;
    private PrincipalScreenPresent principalScreenPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
        configuraAdapter();
        principalScreenPresent = new PrincipalScreenPresent(this);
        principalScreenPresent.obterCategoriasEFilmes();
        btnSearch = (Button) findViewById(R.id.btnSearch);


    }

    private void configuraAdapter() {

        carrosselCategoriaAdapter = new CarrosselCategoriaAdapter(getApplicationContext());

        RecyclerView recyclerView =  findViewById(R.id.categoria_filmes_recycle_view);

        RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(this,3);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(carrosselCategoriaAdapter);
    }


    @Override
    public void adicionarFilmesACategorias(List<CategoriaFilme> categoriasFilmes) {
        carrosselCategoriaAdapter.setCategoriasFilmes(categoriasFilmes);
    }

    @Override
    public void mostraErro() {

    }
}