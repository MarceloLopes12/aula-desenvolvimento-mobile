package dev.marcelo.superflix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private static final String USUARIO_PADRAO = "adm";
    private static final String SENHA_PADRAO = "1234";
    private Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
            }
        });

    }


    private void login() {
        EditText email = (EditText) findViewById(R.id.usuario);
        EditText senha = (EditText) findViewById(R.id.senha);

        if(SENHA_PADRAO.equals(senha.getText().toString()) && USUARIO_PADRAO.equals(email.getText().toString())) {
            Intent intent = new Intent(this, Principal.class);
            startActivity(intent);
        }


    }



}