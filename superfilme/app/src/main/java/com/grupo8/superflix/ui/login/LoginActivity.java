package com.grupo8.superflix.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.grupo8.superflix.R;
import com.grupo8.superflix.data.model.Usuario;
import com.grupo8.superflix.domain.Login;
import com.grupo8.superflix.exception.SenhaIncorretaException;
import com.grupo8.superflix.exception.UsuarioNaoEncontradoException;
import com.grupo8.superflix.ui.principal.PrincipalActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText inputUsuario;
    private EditText inputSenha;
    private FirebaseAuth mAuth;
    private View lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);
        //FirebaseAuth.getInstance().signOut();
        mAuth = FirebaseAuth.getInstance();
        lView = findViewById(R.id.lView);


        btnLogin = (Button) findViewById(R.id.btnSingup);
        inputUsuario = (EditText) findViewById(R.id.email);
        inputSenha = (EditText) findViewById(R.id.senha);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loginUser(inputUsuario.getText().toString(), inputSenha.getText().toString());
            }
        });

    }

        private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("TAG", "signInWithEmail:sucess");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Bem Vindo!",
                                    Toast.LENGTH_SHORT).show();
                            montarTelaPrincipal();

                        } else {
                            Log.w("TAG","signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Email e/ou senha incorreto(s).",
                                    Toast.LENGTH_SHORT).show();
                        }
                }
        });
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

    private boolean userConnected(){
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            return false;
        }
        else{
            return true;
        }
    }

    private void montarTelaPrincipal() {
            actionLogin();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(userConnected()){
            montarTelaPrincipal();
        }
    }

    private void actionLogin() {

        new Thread() {
            @Override
            public void run() {
                Login login = new Login(getApplicationContext());
                Usuario usuario = new Usuario("adm","1234");
                SharedPreferences sp =
                        getSharedPreferences(getString(R.string.preference_usuario_key), Context.MODE_PRIVATE);
            }
        }.start();
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
                    lView.setBackgroundColor(getResources().getColor(R.color.colorWhiteBackground));
                    inputUsuario.setBackground(getDrawable(R.drawable.borda2));
                    inputSenha.setBackground(getDrawable(R.drawable.borda2));
                }else{
                    lView.setBackgroundColor(getResources().getColor(R.color.colorBackground));
                    inputUsuario.setBackground(getDrawable(R.drawable.borda1));
                    inputSenha.setBackground(getDrawable(R.drawable.borda1));
                }
            }
        }
    };
}