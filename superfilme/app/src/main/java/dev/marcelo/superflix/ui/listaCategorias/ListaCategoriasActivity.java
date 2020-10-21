package dev.marcelo.superflix.ui.listaCategorias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.model.Categoria;
import dev.marcelo.superflix.ui.listaFilmes.ListaFilmesActivity;

public class ListaCategoriasActivity extends AppCompatActivity
        implements ListaCategoriasContrato.ListaCategoriasView,
        ListaCategoriasAdapter.ItemCategoriaClickListener {

    private ListaCategoriasAdapter categoriasAdapter;
    private ListaCategoriasContrato.ListaCategoriasPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_categorias);

        configuraAdapter();

        presenter = new ListaCategoriasPresenter(this);
        presenter.obtemCategorias();
    }

    private void configuraAdapter(){
        final RecyclerView recyclerCategorias = findViewById(R.id.recycler_categorias);

        categoriasAdapter = new ListaCategoriasAdapter(this);

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(ListaCategoriasActivity.this);

        recyclerCategorias.setLayoutManager(linearLayoutManager);
        recyclerCategorias.setAdapter(categoriasAdapter);
    }

    @Override
    public void mostraCategorias(List<Categoria> categorias) {
        categoriasAdapter.setCategorias(categorias);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(this, "Erro ao obter lista de categorias.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destruirView();
    }

    @Override
    public void onItemCategoriaClicado(Categoria categoria) {
        Intent intent = new Intent(this, ListaFilmesActivity.class);
        intent.putExtra(ListaFilmesActivity.EXTRA_CATEGORIA, categoria);
        startActivity(intent);
    }
}