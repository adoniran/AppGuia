package com.example.adoniran.guiam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by aluno on 27/12/17.
 */

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logout();
    }



    public void logout(){
    SharedPreferences prefs = getSharedPreferences("meu_arquivo_de_preferencias", 0);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putBoolean("estaLogado", false);
    Intent it =new Intent(this,Login.class);
    startActivity(it);
}

}
