package com.grupo8.superflix.ui.principal;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.grupo8.superflix.R;
import com.grupo8.superflix.ui.categorias.ListaCategoriasFragment;
import com.grupo8.superflix.ui.favoritos.FavoritosFragment;

public class PrincipalActivity extends FragmentActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bottomNavigationView = findViewById(R.id.menu_principal);
        setFrame(new ListaCategoriasFragment());
        setBotaoSelecionado(R.id.btn_categrias);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }


    public void setFrame(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_principal, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void setBotaoSelecionado(@IdRes int idBotaoSelecionado) {
        bottomNavigationView.setSelectedItemId(idBotaoSelecionado);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.btn_inicio:
                break;

            case R.id.btn_favoritos:
                item.setTitle("Favoritos");
                setFrame(new FavoritosFragment());
                break;

            case R.id.btn_categrias:
                item.setTitle("Categorias");
                setFrame(new ListaCategoriasFragment());
                break;
        }

        return true;
    }


}
