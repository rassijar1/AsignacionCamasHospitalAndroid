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

import java.util.Hashtable;
import java.util.Map;

public class Registro extends AppCompatActivity {

    String URL="http://192.168.1.3/hos/registroAdmin.php";

    EditText edtIdadmin, edtNombre, edtApellido, edtUsuario, edtClave,edtFechaNac,edtDireccion,edtTel,edtRespuesta1,edtRespuesta2;
    Button btnregistrar,btnvolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        edtIdadmin=findViewById(R.id.edtidAdminReg);
        edtNombre=findViewById(R.id.edtNombreREg);
        edtApellido=findViewById(R.id.edtApellidoReg);
        edtUsuario = findViewById(R.id.edtUsuarioReg);
        edtClave = findViewById(R.id.edtPasswordReg);
        edtFechaNac=findViewById(R.id.edtFechaNacReg);
        edtDireccion=findViewById(R.id.edtDireccionReg);
        btnregistrar = findViewById(R.id.registrar);
        edtTel=findViewById(R.id.edtTelReg);
        btnvolver=findViewById(R.id.btnVolverReg);
        edtRespuesta1= findViewById(R.id.edtRespuesta1);
        edtRespuesta2= findViewById(R.id.edtRespuesta3);

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrar();

            }
        });

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registro.this, Login.class);
                startActivity(intent);
            }
        });

    }

    public void registrar() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        if(response.equals("error1")) {
                            Toast.makeText(Registro.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("error2")) {
                            Toast.makeText(Registro.this, "Fallo el registro.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("MENSAJE")) {
                            Toast.makeText(Registro.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Registro.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String, String> parametros = new Hashtable<String, String>();
                parametros.put("id_admin", edtIdadmin.getText().toString().trim());
                parametros.put("nombre", edtNombre.getText().toString().trim());
                parametros.put("apellido", edtApellido.getText().toString().trim());
                parametros.put("usuario", edtUsuario.getText().toString().trim());
                parametros.put("clave", edtClave.getText().toString().trim());
                parametros.put("fecha_nac", edtFechaNac.getText().toString().trim());
                parametros.put("direccion", edtDireccion.getText().toString().trim());
                parametros.put("telefono", edtTel.getText().toString().trim());
                parametros.put("respuesta1", edtRespuesta1.getText().toString().trim());
                parametros.put("respuesta2", edtRespuesta2.getText().toString().trim());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
        requestQueue.add(stringRequest);
    }
}
