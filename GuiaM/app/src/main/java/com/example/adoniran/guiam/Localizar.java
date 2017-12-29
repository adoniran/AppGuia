package com.example.adoniran.guiam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import model.Atividade;
import model.LocalizaHTTP;


public class Localizar extends AppCompatActivity {
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localizar);
//        this.queue = Volley.newRequestQueue(this);
        chamarInforCep();
    }


    public void chamarInforCep() {

        Button button=(Button)findViewById(R.id.buscar);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                EditText cep = (EditText) findViewById(R.id.edit_cep);
                TextView resposta = (TextView) findViewById(R.id.etMain_resposta);
                String Cep = cep.getText().toString();
                try{
                    Atividade retorno=new LocalizaHTTP(Cep).execute().get();
                    resposta.setText(retorno.toString());
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });



    }
}
