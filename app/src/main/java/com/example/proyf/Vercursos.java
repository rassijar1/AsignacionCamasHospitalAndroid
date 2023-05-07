package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Vercursos extends AppCompatActivity {

    EditText edtcu,edtnom,edtid;
    Button btninsertar, btnbuscar,btnsalir;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vercursos);
        edtcu=(EditText)findViewById(R.id.edtcod);
        edtnom=(EditText)findViewById(R.id.edtnom);
        btninsertar=(Button)findViewById(R.id.btninsertar);
        edtid=(EditText)findViewById(R.id.edtid) ;
        btnbuscar=(Button)findViewById(R.id.btnbuscar);
        btnsalir= findViewById(R.id.btnSalir);

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



        btninsertar.setOnClickListener(this::onClick);
        btnbuscar.setOnClickListener(this::onClick);
    }


    public void onClick(View v){
        int id= v.getId();
        if(id==R.id.btninsertar){
            insertar("http://192.168.1.2/compdistri/insert.php");

        }else if(id==R.id.btnbuscar){
            buscar();
        }


    }

    private void insertar(String URL){
        //validar que se digiten los datos o que no queden vacias
        if(edtcu.getText().toString().isEmpty()){
            edtcu.setError("Debe digitar el codigo");

        }else if(edtnom.getText().toString().isEmpty()){

            edtnom.setError("Debe digitar el nombre del curso");
        }else{
            //enviar los datos capturados
            StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Se registro el curso ", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            }){



                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("codigo",edtcu.getText().toString());
                    parametros.put("nombre",edtnom.getText().toString());
                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }


    }

    private void buscar(){
        Intent intent= new Intent(this,Editarcursos.class);
        intent.putExtra("id",edtid.getText().toString().trim());
        startActivity(intent);



    }
}