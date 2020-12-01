package com.grupo8.superflix.ui.detalhesfilme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.grupo8.superflix.data.model.Categoria;
import com.grupo8.superflix.ui.categorias.ListaCategoriasContrato;
import com.grupo8.superflix.ui.categorias.ListaCategoriasPresenter;
import com.grupo8.superflix.ui.listafilmes.ListaFilmesContrato;
import com.squareup.picasso.Picasso;

import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Filme;
import com.grupo8.superflix.domain.Favorito;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class DetalhesFilmeActivity extends AppCompatActivity
                implements DetalhesFilmeContrato.DetalhesFilmeView {

    public static final String EXTRA_FILME = "EXTRA_FILME";

    private long idFilme;
    private Filme filme;
    private View dView;

    private DetalhesFilmeContrato.DetalhesFilmePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);
        dView = findViewById(R.id.dView);

        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            String id = uri.getQueryParameter("id");
            idFilme = Long.parseLong(id);
        } else {
             if ("application/json".equals(intent.getType())) {
                try {
                    Log.d("intent", intent.getExtras().toString());
                    for (String k : intent.getExtras().keySet()) {
                        Log.d("intent", k);
                    }
                    JSONObject json = new JSONObject(intent.getExtras().getString("json"));
                    Log.e("intent", json.toString());
                    idFilme = json.getInt("id");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                idFilme = (long) getIntent().getSerializableExtra(EXTRA_FILME);
            }
        }

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(lightSensor != null) {
            mySensorManager.registerListener(
                    lightSensorListener,
                    lightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        presenter = new DetalhesFilmePresenter(idFilme, this);
        presenter.obtemFilme();
    }

    public void adicionarFavorrito(View view) {
        presenter.adicionarFilmeFavoritos(filme, this);
    }

    public void onClickShareFilme(View view) {
        ImageView imagePosterFilme = findViewById(R.id.image_poster_filme);
        Drawable drawable = imagePosterFilme.getDrawable();
        Bitmap b = ((BitmapDrawable)drawable).getBitmap();

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        sendIntent.setType("*/*");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        b.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b,"Titulo da Imagem", null);
        Uri imageUri = Uri.parse(path);

        sendIntent.putExtra(Intent.EXTRA_STREAM, imageUri);

        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://uniritterfilmes.edu.br?id="+filme.getId());

        if(sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(sendIntent, "Share Image"));
        } else {
            Toast.makeText(getApplicationContext(), "falhou", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void mostraFilme(Filme filme) {
        this.filme = filme;
        TextView textTituloFilme = findViewById(R.id.text_titulo_filme);
        ImageView imagePosterFilme = findViewById(R.id.image_poster_filme);
        TextView textDescricaoFilme = findViewById(R.id.text_descricao_filme);

        textDescricaoFilme.setText(filme.getDescricao());
        textTituloFilme.setText(filme.getTitulo());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.getCaminhoPoster())
                .into(imagePosterFilme);
    }

    @Override
    public void mostraErro() {
        Toast.makeText(this, "Erro ao obter lista de filmes", Toast.LENGTH_LONG).show();
    }

    public void removerFavoritos(View view)
    {
        presenter.removerFilmeFavoritos(filme, this);
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
                    dView.setBackgroundColor(getResources().getColor(R.color.colorWhiteBackground));
                }else{
                    dView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                }
            }
        }
    };

}