package com.grupo8.superflix.ui.categorias;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Categoria;
import com.grupo8.superflix.ui.favoritos.FavoritosFragment;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesActivity;

public class ListaCategoriasFragment extends Fragment
        implements ListaCategoriasContrato.ListaCategoriasView,
        ListaCategoriasAdapter.ItemCategoriaClickListener {

    private ListaCategoriasAdapter categoriasAdapter;
    private ListaCategoriasContrato.ListaCategoriasPresenter presenter;
    private RecyclerView recyclerCategorias;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.activity_lista_categorias, container, false);

        recyclerCategorias = view.findViewById(R.id.recycler_categorias);
        configuraAdapter();

        presenter = new ListaCategoriasPresenter(this);
        presenter.obtemCategorias();

        return view;
    }

    private void configuraAdapter(){

        categoriasAdapter = new ListaCategoriasAdapter(this);

        RecyclerView.LayoutManager linearLayoutManager =
                new LinearLayoutManager(getActivity().getApplicationContext());

        recyclerCategorias.setLayoutManager(linearLayoutManager);
        recyclerCategorias.setAdapter(categoriasAdapter);
    }

    @Override
    public void mostraCategorias(List<Categoria> categorias) {
        categoriasAdapter.setCategorias(categorias);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(getActivity().getApplicationContext(),
                "Erro ao obter lista de categorias.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        presenter.destruirView();
        super.onDestroyView();
    }


    @Override
    public void onItemCategoriaClicado(Categoria categoria) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ListaFilmesActivity.class);
        intent.putExtra(ListaFilmesActivity.EXTRA_CATEGORIA, categoria);
        startActivity(intent);
    }

}