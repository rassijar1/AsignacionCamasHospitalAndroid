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

public class EditarArea extends AppCompatActivity {


    EditText edtidareaAct,edtnomareaAct,edtnumhabAct,edtidareaElim;
    Button btneliminarArea, btneditarArea, btnvolverArea;
    String id;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_area);


        requestQueue= Volley.newRequestQueue(this );
       edtidareaAct=findViewById(R.id.edtIdAreaEdit);
       edtnomareaAct=findViewById(R.id.edtNombreAreaEdit);
       edtnumhabAct=findViewById(R.id.edtNumHabEdit);
       edtidareaElim=findViewById(R.id.edtEliminarAreaID);
       btneditarArea=findViewById(R.id.btnActualizarArea);
       btneliminarArea=findViewById(R.id.btnEliminarArea);
       btnvolverArea=findViewById(R.id.btnVolverArea);

        //traer el dato enviado desde el main activity

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            id=bundle.getString("id");


        }
        verArea();
        btneditarArea.setOnClickListener(this::onClick);
        btneliminarArea.setOnClickListener(this::onClick);
        btnvolverArea.setOnClickListener(this::onClick);
    }

    public void onClick(View v){

        int id = v.getId() ;
        if(id==R.id.btnActualizarArea){

            String nomarea=edtnomareaAct.getText().toString().trim();
            String numhab=edtnumhabAct.getText().toString().trim();


            editarArea(nomarea,numhab);
        }else if(id==R.id.btneliminarrPac){

            String cod=edtidareaElim.getText().toString().trim();
            elimArea(cod);
        }if(id==R.id.btnVolverArea){

            Intent intent= new Intent(this, InsertarArea.class);
            startActivity(intent);
        }


    }

    //Funcion de editar

    public void editarArea(String nomarea, String numhab){
        String URL="http://192.168.1.3/hos/EditarArea.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarArea.this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
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
                parametros.put("nomarea",nomarea);
                parametros.put("numhab",numhab);

                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }

    //eliminar

    private void elimArea(String cod){

        String URL="http://192.168.1.3/hos/EliminarArea.php";
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
                parametros.put("cod",edtidareaElim.getText().toString());


                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }




    //ver cursos
    private void verArea(){
        String URL="http://192.168.1.3/hos/MostrarArea.php?id="+id;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String cod, nomarea, numhab;
                try {
                    cod = response.getString("id_area");
                    nomarea = response.getString("nombre");
                    numhab = response.getString("num_habitaciones");


                    edtidareaAct.setText(cod);
                    edtidareaAct.setEnabled(false);
                    edtnomareaAct.setText(nomarea);
                    edtnumhabAct.setText(numhab);


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