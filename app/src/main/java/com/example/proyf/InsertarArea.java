package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InsertarArea extends AppCompatActivity {
    EditText edtidarea,edtidnumhab,edtidbuscararea,edtnomarea;
    Button btninsertarArea, btnbuscararea,btnvolverArea;
    TextView id_area_txt;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_area);


        edtidarea=(EditText) findViewById(R.id.edtIdAreaInsert);
        edtnomarea=(EditText) findViewById(R.id.edtNomAreaInsert);
        edtidnumhab=(EditText) findViewById(R.id.edtNumHabitacionesInsert);
        edtidbuscararea=(EditText) findViewById(R.id.edtBuscarAreaId);
        btninsertarArea=(Button)findViewById(R.id.btnCreateArea);
        btnbuscararea=(Button)findViewById(R.id.btnBuscarArea);
        btnvolverArea=(Button)findViewById(R.id.BtnVolverInsert);
        id_area_txt=(TextView)findViewById(R.id.txtarea);

        btninsertarArea.setOnClickListener(this::onClick);
        btnbuscararea.setOnClickListener(this::onClick);
        btnvolverArea.setOnClickListener(this::onClick);
    }
    public void onClick(View v){
        int id= v.getId();
        if(id==R.id.btnCreateArea){
            insertarArea("http://192.168.1.3/hos/InsertarArea.php");
            GuardarPreferencias();

        }else if(id==R.id.btnBuscarArea){
            buscarArea();
        }else if(id==R.id.BtnVolverInsert){
            Intent intent= new Intent(this, menuAdmin.class);
            startActivity(intent);

        }


    }

    private void insertarArea(String URL){

        //enviar los datos capturados
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Se registro el area ", Toast.LENGTH_SHORT).show();
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
                parametros.put("id_area",edtidarea.getText().toString());
                parametros.put("nombre",edtnomarea.getText().toString());
                parametros.put("num_habitaciones",edtidnumhab.getText().toString());



                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void buscarArea(){
        Intent intent= new Intent(this,EditarArea.class);
        intent.putExtra("id",edtidbuscararea.getText().toString().trim());
        startActivity(intent);




    }
       //guardo el id del area
    public void GuardarPreferencias(){
        SharedPreferences preferences= getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String id_area= edtidarea.getText().toString();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("id_area_fk",id_area);
        id_area_txt.setText(id_area);

        editor.commit();



    }
}