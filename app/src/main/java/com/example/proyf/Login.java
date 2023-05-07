package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.Hashtable;
import java.util.Map;
import android.os.Bundle;

public class Login extends AppCompatActivity {

    String URL ="http://192.168.1.3/hos/login.php";

    EditText etUsuario, etContrasena;
    Button btnLogin, btnRegistrar,btnForgot;
    //CheckBox remember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnForgot=findViewById(R.id.btnForgot);
        /*remember= findViewById(R.id.RememberMe);

        SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox= preferences.getString("remember","");
        if(checkbox.equals("true")){
            login();
           // etUsuario.getText().toString().trim();
            //etContrasena.getText().toString().trim();


        }

       ;*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();

            }
        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, OlvidoClave.class);
                startActivity(intent);
            }
        });

        /*remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){
                    SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    etUsuario.getText().toString();
                    etContrasena.getText().toString().trim();


                    editor.apply();
                    Toast.makeText(Login.this, "Chuleado", Toast.LENGTH_SHORT).show();

                }else{
                    SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    etUsuario.getText().clear();
                    etContrasena.getText().clear();
                    Toast.makeText(Login.this, "No chuleado", Toast.LENGTH_SHORT).show();
                }

            }
        });*/

    }

    public void login() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        if(response.equals("error1")) {
                            Toast.makeText(Login.this, "Se deben de llenar todos los campos.", Toast.LENGTH_SHORT).show();
                        } else if(response.equals("error2")) {
                            Toast.makeText(Login.this, "No existe ese registro.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Inicio de Sesion exitoso.", Toast.LENGTH_LONG).show();

                            //Intent intent = new Intent(Login.this, menuAdmin.class);
                            buscar();
                            //startActivity(intent);



                        }
                        etUsuario.getText().clear();
                        etContrasena.getText().clear();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Login.this, "ERROR AL INICIAR SESION", Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String,String> ();
                parametros.put("usuario", etUsuario.getText().toString().trim());
                parametros.put("clave", etContrasena.getText().toString().trim());

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);

    }
    public void buscar(){
        Intent intent= new Intent(this,EditarDatosAdmin.class);
        intent.putExtra("user",etUsuario.getText().toString().trim());
        startActivity(intent);



    }
}