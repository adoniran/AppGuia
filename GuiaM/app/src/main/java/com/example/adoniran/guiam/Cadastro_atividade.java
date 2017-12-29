package com.example.adoniran.guiam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Atividade;
import model.LocalizaHTTP;

public class Cadastro_atividade extends AppCompatActivity
        {
    private FirebaseAuth mAuth;
    private Atividade retorno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CompletaPeloCep();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEvento();
            }
        });

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

    public void addEvento() {
        mAuth = FirebaseAuth.getInstance();
        Atividade tempUser = GetAtvView();
        DatabaseReference drUsers = FirebaseDatabase.
                getInstance().getReference("atividade");
        drUsers.child(mAuth.getCurrentUser().getUid()).
                setValue(tempUser);
        String msg = "atividade cadastrada";
        Toast.makeText(Cadastro_atividade.this, msg, Toast.LENGTH_SHORT).show();
    }

    public Atividade GetAtvView() {

        String nome, descricao, dataI, data_final, rua, bairro, estado, cidade, pais;
        EditText ed1 = (EditText) findViewById(R.id.editAtv_nome);
        nome = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_desc);
        descricao = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_inicio);
        dataI = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_final);
        data_final = ed1.getText().toString();

        ed1 = (EditText) findViewById(R.id.editAtv_bairro);
        bairro = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_rua);
        rua = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_cidade);
        cidade = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_estado);
        estado = ed1.getText().toString();
        ed1 = (EditText) findViewById(R.id.editAtv_pais);
        pais = ed1.getText().toString();

        Atividade at = new Atividade(
                nome, descricao, dataI, data_final, rua, bairro, estado, cidade, pais
        );
        return at;
    }

    public void CompletaPeloCep() {

        Button button = (Button) findViewById(R.id.buscar2);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cep = (EditText) findViewById(R.id.editAtv_cep);
                String Cep = cep.getText().toString();
                try {
                    retorno = new LocalizaHTTP(Cep).execute().get();
                    EditText edit= (EditText)findViewById(R.id.editAtv_cidade);
                    edit.setText(retorno.getLocalidade(), TextView.BufferType.EDITABLE);

                    edit= (EditText)findViewById(R.id.editAtv_estado);
                    edit.setText(retorno.getUf(), TextView.BufferType.EDITABLE);

                    edit= (EditText)findViewById(R.id.editAtv_rua);
                    edit.setText(retorno.getLogradouro(), TextView.BufferType.EDITABLE);

                    edit= (EditText)findViewById(R.id.editAtv_bairro);
                    edit.setText(retorno.getBairro(), TextView.BufferType.EDITABLE);
                    } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }
}