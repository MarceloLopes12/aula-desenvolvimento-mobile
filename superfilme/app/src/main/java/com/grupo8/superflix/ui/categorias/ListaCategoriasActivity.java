package com.grupo8.superflix.ui.categorias;


import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import java.util.List;

import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Categoria;
import com.grupo8.superflix.ui.favoritos.ListaFavoritosActivity;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesActivity;
import com.grupo8.superflix.ui.principal.MenuPrincipal;

public class ListaCategoriasActivity extends FragmentActivity
        implements ListaCategoriasContrato.ListaCategoriasView,
        ListaCategoriasAdapter.ItemCategoriaClickListener {

    private ListaCategoriasAdapter categoriasAdapter;
    private ListaCategoriasContrato.ListaCategoriasPresenter presenter;
    private  View view;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_categorias);
    }

    @Override
    protected void onStart() {
        MenuPrincipal.configurarMenu(getSupportFragmentManager().beginTransaction(),
                R.id.fragment_menu_categoria, R.id.btn_categrias);

        configuraAdapter();

        presenter = new ListaCategoriasPresenter(this);
        presenter.obtemCategorias();
        super.onStart();
    }

    private void configuraAdapter(){
        final RecyclerView recyclerCategorias = findViewById(R.id.recycler_categorias);

        categoriasAdapter = new ListaCategoriasAdapter(this);

        RecyclerView.LayoutManager linearLayoutManager =
                new LinearLayoutManager(getApplicationContext());

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

    public void onFavoritoClicado(View view) {
        Intent intent = new Intent( this, ListaFavoritosActivity.class);
        startActivity(intent);
    }


}