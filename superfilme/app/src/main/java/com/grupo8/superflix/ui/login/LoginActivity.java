package com.grupo8.superflix.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        inputUsuario = (EditText) findViewById(R.id.usuario);
        inputSenha = (EditText) findViewById(R.id.senha);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                actionLogin();
            }
        });
    }

    private void actionLogin() {

        new Thread() {
            @Override
            public void run() {
                Login login = new Login(getApplicationContext());

                try {
                    Usuario usuario = login.entrar(inputUsuario.getText().toString(), inputSenha.getText().toString());

                    SharedPreferences sp =
                            getSharedPreferences(getString(R.string.preference_usuario_key), Context.MODE_PRIVATE);

                    sp.edit().putInt(getString(R.string.usuario_id), (int) usuario.getUid());

                    montarTelaPrincipal();
                } catch (UsuarioNaoEncontradoException e) {

                } catch (SenhaIncorretaException e) {

                }
            }
        }.start();
    }

    private void montarTelaPrincipal() {
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
    }



}