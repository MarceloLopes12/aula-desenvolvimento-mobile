package dev.marcelo.superflix.ui.listaCategorias;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.data.model.Categoria;

public class ListaCategoriasAdapter extends RecyclerView.Adapter<ListaCategoriasAdapter.ListaCategoriasViewHolder> {

    private List<Categoria> categorias;
    private static ItemCategoriaClickListener itemCategoriaClickListener;

    public ListaCategoriasAdapter(ItemCategoriaClickListener itemCategoriaClickListener) {
        this.itemCategoriaClickListener = itemCategoriaClickListener;

        categorias = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListaCategoriasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new ListaCategoriasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCategoriasViewHolder holder, int position) {
        holder.bind(categorias.get(position));
    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }

    static class ListaCategoriasViewHolder extends RecyclerView.ViewHolder {

        private TextView textTituloCategoria;
        private Categoria categoria;

        public ListaCategoriasViewHolder(@NonNull View itemView) {
            super(itemView);

            textTituloCategoria = itemView.findViewById(R.id.text_titulo_categoria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemCategoriaClickListener != null) {
                        itemCategoriaClickListener.onItemCategoriaClicado(categoria);
                    }
                }
            });
        }

        public void bind(Categoria categoria) {
            this.categoria = categoria;
            textTituloCategoria.setText(categoria.getNome());
        }
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
        notifyDataSetChanged();
    }

    public interface ItemCategoriaClickListener {

        void onItemCategoriaClicado(Categoria categoria);
    }
}
