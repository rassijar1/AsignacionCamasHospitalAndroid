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

public class EditarPaciente extends AppCompatActivity {

    EditText edtidpacelim,edtnompacelim,edtapellidopacelim,edtuserpacelim,edtclavepacelim,edtfechapacelim, edtdireccionpacelim,edtpactelelim,edtelimpac;
    Button btneliminarpac, btneditarpac, btnvolverpac;
    String id;
    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_paciente);


        requestQueue= Volley.newRequestQueue(this );
        edtidpacelim=(EditText)findViewById(R.id.edtElimPacID);
        edtnompacelim=(EditText)findViewById(R.id.edtPacelimNombre);
        edtapellidopacelim=(EditText)findViewById(R.id.edtPacemimApellido);
        edtuserpacelim=(EditText)findViewById(R.id.edtPacelimUsuario);
        edtclavepacelim=(EditText)findViewById(R.id.edtPacelimClave);
        edtfechapacelim=(EditText)findViewById(R.id.edtPacelimfecha);
        edtdireccionpacelim=(EditText)findViewById(R.id.edtPacelimDireccion);
        edtpactelelim=(EditText)findViewById(R.id.edtPacelimTelefono);
        edtelimpac=(EditText)findViewById(R.id.edtElimPac);
        btneditarpac=(Button)findViewById(R.id.btnActualizarPaciente);
        btneliminarpac=(Button)findViewById(R.id.btneliminarrPac);
        btnvolverpac=(Button)findViewById(R.id.btnVolverAct) ;


        //traer el dato enviado desde el main activity

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            id=bundle.getString("id");


        }
        verpac();
        btneditarpac.setOnClickListener(this::onClick);
        btneliminarpac.setOnClickListener(this::onClick);
        btnvolverpac.setOnClickListener(this::onClick);
    }

    public void onClick(View v){

        int id = v.getId() ;
        if(id==R.id.btnActualizarPaciente){

            String nompac=edtnompacelim.getText().toString().trim();
            String appac=edtapellidopacelim.getText().toString().trim();
            String dirpac=edtdireccionpacelim.getText().toString().trim();
            String uspac=edtuserpacelim.getText().toString().trim();
            String clapac=edtclavepacelim.getText().toString().trim();
            String fechapac=edtfechapacelim.getText().toString().trim();
            String telpac= edtpactelelim.getText().toString().trim();

            editarpac(nompac,appac,dirpac,uspac,clapac,fechapac,telpac);
        }else if(id==R.id.btneliminarrPac){

            String cod=edtelimpac.getText().toString().trim();
            elimpac(cod);
        }if(id==R.id.btnVolverAct){

            Intent intent= new Intent(this, InsertarPaciente.class);
            startActivity(intent);
        }


    }

    //Funcion de editar

    public void editarpac(String nompac, String appac,String dirpac, String uspac,String clapac,String fechapac, String telpac){
        String URL="http://192.168.1.3/hos/EditarPaciente.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarPaciente.this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
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
                parametros.put("nompac",nompac);
                parametros.put("appac",appac);
                parametros.put("dirpac",dirpac);
                parametros.put("uspac",uspac);
                parametros.put("clapac",clapac);
                parametros.put("fechapac",fechapac);
                parametros.put("telpac",telpac);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }

    //eliminar

    private void elimpac(String cod){

        String URL="http://192.168.1.3/hos/EliminarPaciente.php";
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
                parametros.put("cod",edtelimpac.getText().toString());


                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }




    //ver cursos
    private void verpac(){
        String URL="http://192.168.1.3/hos/MostrarPaciente.php?id="+id;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String cod, nom, apell,dir,usua,clav,fechan,tele;
                try {
                    cod = response.getString("id_paciente");
                    nom = response.getString("nombre");
                    apell = response.getString("apellido");
                    dir= response.getString("direccion");
                    usua = response.getString("usuario");
                    clav= response.getString("clave");
                    fechan = response.getString("fecha_nac");
                    tele= response.getString("telefono");

                    edtidpacelim.setText(cod);
                    edtidpacelim.setEnabled(false);
                    edtnompacelim.setText(nom);
                    edtapellidopacelim.setText(apell);
                    edtdireccionpacelim.setText(dir);
                    edtuserpacelim.setText(usua);
                    edtclavepacelim.setText(clav);
                    edtfechapacelim.setText(fechan);
                    edtpactelelim.setText(tele);

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