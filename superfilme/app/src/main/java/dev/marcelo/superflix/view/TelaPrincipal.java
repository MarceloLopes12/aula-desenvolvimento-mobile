package dev.marcelo.superflix.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.ui.detalhesFilme.DetalhesFilmeActivity;

public class TelaPrincipal extends AppCompatActivity {

    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                actionSearch();
            }
        });
    }

    private void actionSearch() {
            montarTelaDetalhes();
    }

    private void montarTelaDetalhes() {
        Intent intent = new Intent(this, DetalhesFilmeActivity.class);
        startActivity(intent);
    }
}