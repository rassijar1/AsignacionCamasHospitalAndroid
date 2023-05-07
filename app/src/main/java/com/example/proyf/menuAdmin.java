package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class menuAdmin extends AppCompatActivity {
    Button btnsalir, btninsertarPaciente,btninsertarArea,btninsertarenfermera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

            btnsalir=findViewById(R.id.btnExit);
            btninsertarPaciente=findViewById(R.id.btnAgregarPaciente);
            btninsertarArea=findViewById(R.id.btnAgregarArea);
            btninsertarenfermera=findViewById(R.id.btninsertarEnfermera);


        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();*/

                finish();

            }
        });
            btninsertarPaciente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(menuAdmin.this, InsertarPaciente.class);
                    startActivity(intent);

                }
            });

               btninsertarArea.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(menuAdmin.this, InsertarArea.class);
                       startActivity(intent);
                       
                   }
               });

               btninsertarenfermera.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Intent intent = new Intent(menuAdmin.this, Insertar_enfermera.class);
                       startActivity(intent);
                   }
               });



    }





}