package com.example.adoniran.guiam;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Usuario;



public class Cadastro extends AppCompatActivity {
    private EditText Email;
    private EditText Password;
    private EditText name;
        private FirebaseAuth mAuth;
    private String sexo;
    private String nome;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        addsexo();

    }
    public void buttonSignUpClick(View view) {
        Email=(EditText) findViewById(R.id.edit_email);
        Password=(EditText)findViewById(R.id.edit_password);
        name=(EditText)findViewById(R.id.edit_name);


        email = Email.getText().toString();
        String password = Password.getText().toString();
        nome = name.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        String msg = task.isSuccessful() ? "SIGN UP OK!":"SIGN UP ERROR!";
                        Toast.makeText(Cadastro.this, msg,Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            Usuario tempUser = new Usuario(nome, email,sexo);
                            DatabaseReference drUsers = FirebaseDatabase.
                                    getInstance().getReference("usuario");
                            drUsers.child(mAuth.getCurrentUser().getUid()).
                                    setValue(tempUser);
                            redireciona();
                        }

                    }

                });

    }
    public void redireciona(){
        Intent it =new Intent(this,Login.class);
        startActivity(it);
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

    public void addsexo(){
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup1);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton_f:

                        sexo="Feminino";

                        break;
                    case R.id.radioButton_m:
                        sexo="Masculino";

                        break;
                    case R.id.radioButton_o:
                        sexo="Outro";

                        break;
                }
            }
        });
    }




}
