package dev.marcelo.superflix.ui.telaprincipal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.model.CategoriaFilme;
import dev.marcelo.superflix.data.model.Filme;
import dev.marcelo.superflix.ui.listaFilmes.ListaFilmesAdapter;

public class CarrosselCategoriaAdapter extends RecyclerView.Adapter<CarrosselCategoriaAdapter.CarrosselCategoriaViewHolder> {

    private List<CategoriaFilme> categoriasFilmes;
    private static Context context;


    public CarrosselCategoriaAdapter(Context context) {
        categoriasFilmes = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public CarrosselCategoriaAdapter.CarrosselCategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filme, parent, false);

        return new CarrosselCategoriaAdapter.CarrosselCategoriaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarrosselCategoriaAdapter.CarrosselCategoriaViewHolder holder, int position) {
        holder.bind(categoriasFilmes.get(position));
    }

    @Override
    public int getItemCount() {
        return (categoriasFilmes != null && categoriasFilmes.size() > 0) ? categoriasFilmes.size() : 0;
    }

    static class CarrosselCategoriaViewHolder extends RecyclerView.ViewHolder {

        private TextView categoriaNome;
        private RecyclerView recyclerViewFilmeCategoria;
        private ListaFilmesAdapter adapterFilmeCategoria;
        private CategoriaFilme categoriaFilme;
        private View itemView;

        public CarrosselCategoriaViewHolder(@NonNull View itemView) {
            super(itemView);
            categoriaNome = itemView.findViewById(R.id.categoria_nome);
            recyclerViewFilmeCategoria = itemView.findViewById(R.id.categoria_filmes_recycle_view);
            this.itemView = itemView;
            configuraAdapter();

        }

        public void bind(CategoriaFilme categoriaFilme) {
            this.categoriaFilme = categoriaFilme;

            categoriaNome.setText(categoriaFilme.getCategoria());
            adapterFilmeCategoria.setFilmes(categoriaFilme.getFilmes());

        }

        private void configuraAdapter() {

            adapterFilmeCategoria = new ListaFilmesAdapter(new ListaFilmesAdapter.ItemFilmeClickListener() {
                @Override
                public void onItemFilmeClicado(Filme filme) {

                }
            });

            RecyclerView.LayoutManager linearLayoutManager = new GridLayoutManager(itemView.getContext(),3);

            recyclerViewFilmeCategoria.setLayoutManager(linearLayoutManager);

            recyclerViewFilmeCategoria.setAdapter(adapterFilmeCategoria);
        }


    }

    public void setCategoriasFilmes(List<CategoriaFilme> categoriasFilmes) {
        this.categoriasFilmes = categoriasFilmes;
        notifyDataSetChanged();
    }

}
