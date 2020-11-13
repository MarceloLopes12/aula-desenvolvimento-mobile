package com.grupo8.superflix.ui.favoritos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.ui.detalhesfilme.DetalhesFilmeActivity;
import com.grupo8.superflix.data.model.Usuario;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesAdapter;

public class FavoritosFragment extends Fragment
        implements FavoritosContrato.ListaFavoritosView,
        ListaFilmesAdapter.ItemFilmeClickListener {

    private Usuario usuario;
    private ListaFilmesAdapter filmesAdapter;
    private FavoritosContrato.ListaFavoritosPresenter presenter;
    private FirebaseAuth mAuth;

    public static final String EXTRA_USUARIO = "EXTRA_USUARIO";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.tela_favoritos, container, false);

        return view;
    }

    @Override
    public void onStart() {
        usuario = new Usuario("ADM","1234");//(Usuario) getActivity().getIntent().getSerializableExtra(EXTRA_USUARIO);

        configuraAdapter();

        presenter = new FavoritosPresenter(usuario, this);

        presenter.obtemFavoritos(getActivity().getApplication());
        super.onStart();

    }


    private void configuraAdapter() {
        final RecyclerView recyclerFilmes = getActivity().findViewById(R.id.favoritos_lista);

        filmesAdapter = new ListaFilmesAdapter(this);

        RecyclerView.LayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity().getApplicationContext(), 2);

        recyclerFilmes.setLayoutManager(gridLayoutManager);

        recyclerFilmes.setAdapter(filmesAdapter);
    }

    @Override
    public void mostraFavoritos(List<Filme> favoritos) {
        filmesAdapter.setFilmes(favoritos);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(getActivity().getApplicationContext(), "Erro ao obter lista de filmes",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destruirView();
    }


    @Override
    public void onItemFilmeClicado(Filme filme) {
        Intent intent =
                new Intent( getActivity().getApplicationContext(), DetalhesFilmeActivity.class);
        intent.putExtra(DetalhesFilmeActivity.EXTRA_FILME, filme.getId());
        startActivity(intent);
    }
}