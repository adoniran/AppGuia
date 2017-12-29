package com.example.adoniran.guiam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Atividade;
import model.LocalizaHTTP;

public class SearchAtividade extends AppCompatActivity {
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_atividade);
            onClick();



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
        public void onClick(){
            Button button = (Button) findViewById(R.id.button2);
            button.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText ed=(EditText)findViewById(R.id.Searr);
                    String passa=ed.getText().toString();
                    listner(passa);

                }

            });

        }


        public void listner(String s){

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("atividade");
            ref.orderByChild("nome").equalTo(s).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    TextView resposta = (TextView) findViewById(R.id.resposta);
                    resposta.setText(dataSnapshot.getValue(Atividade.class).toString());

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
}
