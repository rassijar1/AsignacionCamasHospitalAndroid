package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ValidarPreguntas extends AppCompatActivity {

    String URL ="http://192.168.1.4/hos/ValidarPreguntas.php";

    EditText edtRta1,edtRta2;
    Button btnconfirmar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_preguntas);

        edtRta1 = findViewById(R.id.edtRta1);
        edtRta2 = findViewById(R.id.edtRta2);
        btnconfirmar=findViewById(R.id.btnConfirmar);



        btnconfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarPreguntas();

            }
        });




    }

    public void validarPreguntas() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        if(response.equals("VACIO")) {
                            Toast.makeText(ValidarPreguntas.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("NO CONSULTO")) {
                            Toast.makeText(ValidarPreguntas.this, "No existe ese registro.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ValidarPreguntas.this, "Inicio de Sesion exitoso.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ValidarPreguntas.this, ValidarId.class);
                            startActivity(intent);



                        }
                        // edtAdmin.getText().clear();
                        //edtUsua.getText().clear();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ValidarPreguntas.this, "ERROR AL INICIAR SESION", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String,String>();
                parametros.put("respuesta1", edtRta1.getText().toString().trim());
                parametros.put("respuesta2", edtRta2.getText().toString().trim());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ValidarPreguntas.this);
        requestQueue.add(stringRequest);
    }
}