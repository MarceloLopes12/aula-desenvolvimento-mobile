package com.grupo8.superflix.ui.principal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.grupo8.superflix.R;
import com.grupo8.superflix.ui.categorias.ListaCategoriasActivity;
import com.grupo8.superflix.ui.favoritos.ListaFavoritosActivity;

public class MenuPrincipal extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private int botaoSelecionado;

    public MenuPrincipal(int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        bottomNavigationView = view.findViewById(R.id.menu_principal);
        configurarBottonNavigation();
        return view;
    }


    public static void configurarMenu(FragmentTransaction fragmentTransaction,
                                      @IdRes int containerViewI, @IdRes int botaoSelecionado) {
        MenuPrincipal menuPrincipal = new MenuPrincipal(botaoSelecionado);
        menuPrincipal.setBotaoSelecionado(botaoSelecionado);
        fragmentTransaction.replace(containerViewI, menuPrincipal);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }

    private void setBotaoSelecionado(@IdRes int botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;

    }

    private void configurarBottonNavigation() {

        bottomNavigationView.setSelectedItemId(botaoSelecionado);

        bottomNavigationView.setOnNavigationItemSelectedListener( new BottomNavigationView
                .OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.btn_inicio:
                        break;

                    case R.id.btn_favoritos:
                        Intent intent = new Intent( getActivity().getApplicationContext(), ListaFavoritosActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.btn_categrias:
                        Intent intentCategoria = new Intent( getActivity().getApplicationContext(), ListaCategoriasActivity.class);
                        startActivity(intentCategoria);
                        break;
                }

                return true;
            }
        });
    }


}
