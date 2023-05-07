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

public class RecuperarClave extends AppCompatActivity {
    EditText edtpass,edtdisable;
    Button btnrestablecer;
    String id;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_clave);
        requestQueue= Volley.newRequestQueue(this );
        edtpass=(EditText) findViewById(R.id.edtPass);
        edtdisable=(EditText) findViewById(R.id.edtIddisable);
        btnrestablecer=(Button)findViewById(R.id.btnRestablecer);
        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            id=bundle.getString("id");


        }
        ver();
        btnrestablecer.setOnClickListener(this::onClick);

    }


    public void onClick(View v){

        int id = v.getId() ;
        if(id==R.id.btnRestablecer){

            String password=edtpass.getText().toString().trim();
            restablecer(password);
        }


    }


    public void restablecer(String password){
        String URL="http://192.168.1.4/hos/RestablecerClave.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RecuperarClave.this, "Se actualizo con exito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecuperarClave.this, Login.class);
                startActivity(intent);
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
                parametros.put("id",id);
                parametros.put("password",password);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void ver(){
        String URL="http://192.168.1.6/hos/ver.php?id="+id;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String pass, cod;
                try {
                    cod = response.getString("id_admin");
                    pass = response.getString("clave");
                    edtdisable.setText(cod);
                    edtdisable.setEnabled(false);

                    edtpass.setText(pass);
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