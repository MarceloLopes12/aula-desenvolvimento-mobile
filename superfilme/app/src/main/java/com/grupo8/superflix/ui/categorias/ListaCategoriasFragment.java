package com.grupo8.superflix.ui.categorias;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import java.util.List;

import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Categoria;
import com.grupo8.superflix.ui.favoritos.FavoritosFragment;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesActivity;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesContrato;

import static androidx.core.content.ContextCompat.getSystemService;

public class ListaCategoriasFragment extends Fragment
        implements ListaCategoriasContrato.ListaCategoriasView,
        ListaCategoriasAdapter.ItemCategoriaClickListener {

    private ListaCategoriasAdapter categoriasAdapter;
    private ListaCategoriasContrato.ListaCategoriasPresenter presenter;
    private ListaFilmesContrato.ListaFilmesPresenter presenterFilmes;
    private RecyclerView recyclerCategorias;
    private SensorManager mSensorManager;
    private Button btnSearch;
    private EditText inputTitle;
    private View CView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater
                .inflate(R.layout.activity_lista_categorias, container, false);

        btnSearch = (Button) view.findViewById(R.id.btnSearch);
        inputTitle = (EditText) view.findViewById(R.id.search);

        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onSearchTitle(inputTitle.getText().toString());
            }
        });

        CView = view.findViewById(R.id.CView);
        recyclerCategorias = view.findViewById(R.id.recycler_categorias);
        configuraAdapter();

        presenter = new ListaCategoriasPresenter(this);
        presenter.obtemCategorias();

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null) {
            mSensorManager.registerListener(
                    lightSensorListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

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

    public void onSearchTitle(String titulo) {
        Intent intent = new Intent(getActivity().getApplicationContext(), ListaFilmesActivity.class);
        intent.putExtra(ListaFilmesActivity.EXTRA_TITULO, titulo);
        startActivity(intent);
    }

    private final SensorEventListener lightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
                if(event.values[0] >= 50) {
                    CView.setBackgroundColor(getResources().getColor(R.color.colorWhiteBackground));
                    recyclerCategorias.setBackgroundColor(getResources().getColor(R.color.colorWhiteBackground));
                    inputTitle.setBackground(getResources().getDrawable(R.drawable.borda2));
                }else{
                    CView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                    inputTitle.setBackground(getResources().getDrawable(R.drawable.borda1));
                    recyclerCategorias.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                }
            }
        }
    };
}