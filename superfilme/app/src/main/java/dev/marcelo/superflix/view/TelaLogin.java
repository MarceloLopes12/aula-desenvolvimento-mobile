package dev.marcelo.superflix.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import dev.marcelo.superflix.R;
import dev.marcelo.superflix.domain.Login;
import dev.marcelo.superflix.exception.SenhaIncorretaException;
import dev.marcelo.superflix.exception.UsuarioNaoEncontradoException;

public class TelaLogin extends AppCompatActivity {

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
                    login.entrar(inputUsuario.getText().toString(), inputSenha.getText().toString());
                    montarTelaPrincipal();
                } catch (UsuarioNaoEncontradoException e) {

                } catch (SenhaIncorretaException e) {

                }
            }
        }.start();

    }

    private void montarTelaPrincipal() {
            Intent intent = new Intent(this, TelaPrincipal.class);
            startActivity(intent);
    }



}