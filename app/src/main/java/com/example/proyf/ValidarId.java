package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;

public class ValidarId extends AppCompatActivity {

    EditText edtiden;
    Button  btnverificar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_id);
        edtiden=(EditText) findViewById(R.id.edtIdentificacion);
        btnverificar=(Button) findViewById(R.id.btnVerificar);

        btnverificar.setOnClickListener(this::onClick);

    }

    public void onClick(View v){
        int id= v.getId();
        if(id==R.id.btnVerificar){
            verificar();

        }




    }

    private void verificar(){
        Intent intent= new Intent(this, RecuperarClave.class);
        intent.putExtra("id",edtiden.getText().toString().trim());
        startActivity(intent);



    }
}