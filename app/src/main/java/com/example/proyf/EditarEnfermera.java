package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditarEnfermera extends AppCompatActivity {
    EditText edtidenferact,edtnomenferact,edtapellidoenferact,edtfechaenferact, edtccenferact,edtelimenfer;
    Button btnactenfer, btnelimrenfer,btnvolverenferact;
    String id;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_enfermera);
        requestQueue= Volley.newRequestQueue(this );
        edtidenferact=(EditText)findViewById(R.id.edtIdEnferEdit);
        edtnomenferact=(EditText)findViewById(R.id.edtNomEnferEdit);
        edtapellidoenferact=(EditText)findViewById(R.id.edtApellidoEnferEdit);
        edtfechaenferact=(EditText)findViewById(R.id.edtFechaNacEnferEdit);
        edtelimenfer=(EditText)findViewById(R.id.edtElimEnfer);
        edtccenferact=(EditText)findViewById(R.id.edtCcEnferEdit);
        btnactenfer=(Button)findViewById(R.id.btnActEnfer);
        btnelimrenfer=(Button)findViewById(R.id.btnElimEnfer);
        btnvolverenferact=(Button)findViewById(R.id.btnVolverEditEnfer);


        //traer el dato enviado desde el main activity

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            id=bundle.getString("id");


        }
        verenfer();
        btnactenfer.setOnClickListener(this::onClick);
        btnelimrenfer.setOnClickListener(this::onClick);
        btnvolverenferact.setOnClickListener(this::onClick);


    }

    public void onClick(View v){

        int id = v.getId() ;
        if(id==R.id.btnActEnfer){

            String nomEnfer=edtnomenferact.getText().toString().trim();
            String apEnfer=edtapellidoenferact.getText().toString().trim();
            String fechaEnfer=edtfechaenferact.getText().toString().trim();
            String ccEnfer= edtccenferact.getText().toString().trim();

            editarenfer(nomEnfer,apEnfer,fechaEnfer,ccEnfer);
        }else if(id==R.id.btnElimEnfer){

            String cod=edtelimenfer.getText().toString().trim();
            elimEnfer(cod);
        }if(id==R.id.btnVolverEditEnfer){

            Intent intent= new Intent(this, Insertar_enfermera.class);
            startActivity(intent);
        }


    }

    //Funcion de editar

    public void editarenfer(String nomEnfer, String apEnfer,String fechaEnfer,String ccEnfer){
        String URL="http://192.168.1.3/hos/EditarEnfermera.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarEnfermera.this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("id",id);
                parametros.put("nomEnfer",nomEnfer);
                parametros.put("apEnfer",apEnfer);
                parametros.put("fechaEnfer",fechaEnfer);
                parametros.put("ccEnfer",ccEnfer);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }

    //eliminar

    private void elimEnfer(String cod){

        String URL="http://192.168.1.3/hos/EliminarEnfermera.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("cod",edtelimenfer.getText().toString());


                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }




    //ver cursos
    private void verenfer(){
        String URL="http://192.168.1.3/hos/MostrarEnfermera.php?id="+id;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String cod, nom, apell,fechan,cc;
                try {
                    cod = response.getString("id_enfermera");
                    nom = response.getString("nombre");
                    apell = response.getString("apellido");
                    fechan = response.getString("fecha_nac");
                    cc= response.getString("cc");

                    edtidenferact.setText(cod);
                    edtidenferact.setEnabled(false);
                    edtnomenferact.setText(nom);
                    edtapellidoenferact.setText(apell);

                    edtfechaenferact.setText(fechan);
                    edtccenferact.setText(cc);

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }

        );
        requestQueue.add(jsonObjectRequest);

    }
}