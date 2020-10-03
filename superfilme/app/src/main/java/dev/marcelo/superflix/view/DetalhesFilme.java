package dev.marcelo.superflix.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.domain.model.Filme;

public class DetalhesFilme extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        onclickInsereFilme();
    }

    public void onclickInsereFilme() {
        Filme filme = new Filme("Poeira em Alto-Mar", "Lorem ipsum dollor sit amet.", "", 2020, 1234);
        Intent it = new Intent();

        it.putExtra("filme", filme);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmento = fm.findFragmentById(R.id.fragmentFilmeDetalhe);
        ((FilmeFragment) fragmento).setFilme(filme);
    }
}