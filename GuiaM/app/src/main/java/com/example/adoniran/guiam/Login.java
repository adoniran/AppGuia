package com.example.adoniran.guiam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import model.Usuario;

public class Login extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
        boolean jaLogou = prefs.getBoolean("estaLogado", false);

        if(jaLogou==true) {
            Intent it =new Intent(this,MainActivity.class);
            startActivity(it);
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_geral, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.cada:
                Intent it =new Intent(this,Cadastro.class);
                startActivity(it);
                return true;
            case R.id.log:
                Intent vit =new Intent(this,Login.class);
                startActivity(vit);
                return true;
            case R.id.sss:
                Intent vita =new Intent(this,SearchAtividade.class);
                startActivity(vita);
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
    public void buttonSignInClick(View view) {
        Email=(EditText) findViewById(R.id.edit_email_log);
        Password=(EditText)findViewById(R.id.edit_password_log);

        String login = Email.getText().toString();

        String passwd = Password.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(login, passwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {

                String msg = task.isSuccessful() ? "SIGN IN OK!":"SIGN IN ERROR!";
                Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("estaLogado", true);

                    editor.commit();
                    redireciona();
                }


//
            }

        });


    }
    public void redireciona(){
        Intent it =new Intent(this,MainActivity.class);
        startActivity(it);
    }
}
