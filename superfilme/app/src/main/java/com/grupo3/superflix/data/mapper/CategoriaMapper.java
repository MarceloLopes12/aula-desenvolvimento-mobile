package com.grupo3.superflix.data.mapper;

import java.util.ArrayList;
import java.util.List;

import com.grupo3.superflix.data.model.Categoria;
import com.grupo3.superflix.data.network.response.categoria.CategoriaResponse;

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
