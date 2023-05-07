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

public class Insertar_enfermera extends AppCompatActivity {
    SharedPreferences preferences;

    EditText edtidenfer,edtnomenfer,edtapellidoenfer,edtfechaenfer, edtccenfer,edtenf_id_admin_fk,edtenf_id_area_fk,edtbuscarenfer;
    Button btninsertarenfer, btnbuscarenfer,btnvolverenfer;
    TextView id_enfermera_txt;
    RequestQueue requestQueue;
    //paciente_fk pac= new paciente_fk();

    EditarDatosAdmin ob= new EditarDatosAdmin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);


        setContentView(R.layout.activity_insertar_enfermera);
        edtidenfer=(EditText)findViewById(R.id.edtIdEnferInsert);

        edtnomenfer=(EditText)findViewById(R.id.edtNomEnferInsert);
        edtapellidoenfer=(EditText)findViewById(R.id.edtApellidoEnferInsert);
        edtfechaenfer=(EditText)findViewById(R.id.edtFechaNacEnferInsert);
        edtbuscarenfer=(EditText)findViewById(R.id.edtBuscarEnfer);
        edtccenfer=(EditText)findViewById(R.id.edtCcEnferInsert);
        edtenf_id_admin_fk=(EditText)findViewById(R.id.edtEnferIdAdminFK);
        edtenf_id_area_fk=(EditText)findViewById(R.id.edtEnferIdAreaFK);
        id_enfermera_txt=(TextView)findViewById(R.id.txtenfermera);
        btninsertarenfer=(Button)findViewById(R.id.btnCreateEnfer);
        btnbuscarenfer=(Button)findViewById(R.id.btnBuscarEnfer);
        btnvolverenfer=(Button)findViewById(R.id.btnVolverEnfer);
        String id_admin=preferences.getString("id_admin_fk",null); //foranea admin
        String id_area=preferences.getString("id_area_fk",null);//foranea area

        if (id_admin!=null){
            edtenf_id_admin_fk.setText(id_admin);
        }
        if (id_area!=null){
            edtenf_id_area_fk.setText(id_area);
        }

        edtenf_id_area_fk.setEnabled(false);
        edtenf_id_admin_fk.setEnabled(false);





        btninsertarenfer.setOnClickListener(this::onClick);
        btnbuscarenfer.setOnClickListener(this::onClick);
        btnvolverenfer.setOnClickListener(this::onClick);
    }


    public void onClick(View v){
        int id= v.getId();
        if(id==R.id.btnCreateEnfer){
            insertarEnfermera("http://192.168.1.3/hos/InsertarEnfermera.php");
            GuardarPreferencias();

        }else if(id==R.id.btnBuscarEnfer){
            buscarEnfermera();
        }else if(id==R.id.btnVolverEnfer){
            Intent intent= new Intent(this, menuAdmin.class);
            startActivity(intent);

        }

    }

    private void insertarEnfermera(String URL){

        //enviar los datos capturados
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Se registro la enfermera ", Toast.LENGTH_SHORT).show();
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
                parametros.put("id_enfermera",edtidenfer.getText().toString());
                parametros.put("nombre",edtnomenfer.getText().toString());
                parametros.put("apellido",edtapellidoenfer.getText().toString());
                parametros.put("fecha_nac",edtfechaenfer.getText().toString());
                parametros.put("cc",edtccenfer.getText().toString());
                parametros.put("id_admin_fk",edtenf_id_admin_fk.getText().toString());
                parametros.put("id_area_fk",edtenf_id_area_fk.getText().toString());


                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }

    private void buscarEnfermera(){
        Intent intent= new Intent(this,EditarEnfermera.class);
        intent.putExtra("id",edtbuscarenfer.getText().toString().trim());
        startActivity(intent);



    }
    //guardo el id de la enfemera como fk
    public void GuardarPreferencias(){
        SharedPreferences preferences= getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String id_enfermera= edtidenfer.getText().toString();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("id_enfermera_fk",id_enfermera);
        id_enfermera_txt.setText(id_enfermera);

        editor.commit();



    }
}






