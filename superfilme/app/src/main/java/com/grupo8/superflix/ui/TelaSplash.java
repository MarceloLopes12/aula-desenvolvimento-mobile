package com.grupo8.superflix.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.grupo8.superflix.R;
import com.grupo8.superflix.ui.login.LoginActivity;

public class TelaSplash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                montarTelaLogin();
            }
        },2000);

    }

    private void montarTelaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
