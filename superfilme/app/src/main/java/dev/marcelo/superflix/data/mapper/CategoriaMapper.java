package dev.marcelo.superflix.data.mapper;

import java.util.ArrayList;
import java.util.List;

import dev.marcelo.superflix.data.model.Categoria;
import dev.marcelo.superflix.data.network.response.categoria.CategoriaResponse;

public class CategoriaMapper {

    public static List<Categoria> deResponseParaDominio(List<CategoriaResponse> listaCategoriaRespons) {
        List<Categoria> listaCategorias = new ArrayList<>();

        for (CategoriaResponse categoriaResponse : listaCategoriaRespons){
            final Categoria categoria = new Categoria(categoriaResponse.getNomeCategoria(), categoriaResponse.getIdCategoria());
            listaCategorias.add(categoria);
        }

        return listaCategorias;
    }
}
