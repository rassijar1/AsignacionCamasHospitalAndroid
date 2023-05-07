package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Hashtable;
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

public class OlvidoClave extends AppCompatActivity {

    String URL ="http://192.168.1.4/hos/OlvidoClave.php";

    EditText edtUsua;
    Button btnvalidar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvido_clave);

        edtUsua = findViewById(R.id.edtUser);

        btnvalidar=findViewById(R.id.btnValidar);



        btnvalidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clave();

            }
        });




    }

    public void Clave() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        if(response.equals("VACIO")) {

                            Toast.makeText(OlvidoClave.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("NOCONSULTO")) {
                            Toast.makeText(OlvidoClave.this, "No existe ese registro.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(OlvidoClave.this, "Inicio de Sesion exitoso.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(OlvidoClave.this, ValidarPreguntas.class);
                            startActivity(intent);



                        }
                       // edtAdmin.getText().clear();
                        //edtUsua.getText().clear();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(OlvidoClave.this, "ERROR AL INICIAR SESION", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new Hashtable<String,String> ();
                parametros.put("usuario", edtUsua.getText().toString().trim());


                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(OlvidoClave.this);
        requestQueue.add(stringRequest);
    }
}