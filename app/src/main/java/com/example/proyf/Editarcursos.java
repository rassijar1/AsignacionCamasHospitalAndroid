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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Editarcursos extends AppCompatActivity {

    EditText edtcur, edtnomc,edtcod;
    Button btneliminar, btneditar, btnvolver;
    String id;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editarcursos);


        requestQueue= Volley.newRequestQueue(this );
        edtcur=(EditText) findViewById(R.id.edtcur);
        edtnomc=(EditText) findViewById(R.id.edtnomc);
        edtcod=(EditText)findViewById(R.id.edtcod);
        btneliminar=(Button)findViewById(R.id.btneliminar);
        btneditar=(Button)findViewById(R.id.btneditar);
        btnvolver=(Button)findViewById(R.id.btnvolver);

        //traer el dato enviado desde el main activity

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            id=bundle.getString("id");


        }
        vercurso();
        btneditar.setOnClickListener(this::onClick);
        btneliminar.setOnClickListener(this::onClick);
        btnvolver.setOnClickListener(this::onClick);
    }

    public void onClick(View v){

        int id = v.getId() ;
        if(id==R.id.btneditar){

            String nomc=edtnomc.getText().toString().trim();
            editarcur(nomc);
        }else if(id==R.id.btneliminar){

            String cod=edtcod.getText().toString().trim();
            elimcur(cod);
        }if(id==R.id.btnvolver){

            Intent intent= new Intent(this, Vercursos.class);
            startActivity(intent);
        }


    }

    //Funcion de editar

    public void editarcur(String nomc){
        String URL="http://192.168.1.4/hos/editar.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Editarcursos.this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
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
                parametros.put("nomc",nomc);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }

    //eliminar

    private void elimcur(String cod){

        String URL="http://192.168.1.4/hos/elim.php";
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
                parametros.put("cod",edtcod.getText().toString());


                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }




    //ver cursos
    private void vercurso(){
        String URL="http://192.168.1.2/compdistri/mostrar.php?id="+id;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String cod, nom;
                try {
                    cod = response.getString("cod_c");
                    nom = response.getString("nomb_c");
                    edtcur.setText(cod);
                    edtnomc.setText(nom);
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