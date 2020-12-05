package com.grupo8.superflix.ui.principal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.grupo8.superflix.R;
import com.grupo8.superflix.ui.categorias.ListaCategoriasFragment;
import com.grupo8.superflix.ui.favoritos.FavoritosFragment;

public class PrincipalActivity extends FragmentActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private View pView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        pView = findViewById(R.id.menu_principal);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        bottomNavigationView = findViewById(R.id.menu_principal);
        setFrame(new ListaCategoriasFragment());
        setBotaoSelecionado(R.id.btn_categrias);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    protected void onPause() {
        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensorManager.unregisterListener(lightSensorListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null) {
            mySensorManager.registerListener(
                    lightSensorListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        super.onResume();
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

    private final SensorEventListener lightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT) {
                if(event.values[0] >= 50) {
                    pView.setBackgroundColor(getResources().getColor(R.color.colorWhiteBackground));
                }else{
                    pView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                }
            }
        }
    };


}
