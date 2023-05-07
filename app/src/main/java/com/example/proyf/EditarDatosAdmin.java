package com.example.proyf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditarDatosAdmin extends AppCompatActivity {
    EditText edtIdadminUpdate,edtNombreUpdate,edtApellidoUpdate,edtUsuarioUpdate,edtClaveUpdate,edtFechaNacUpdate,edtDireccionUpdate,edtTelUpdate;
    Button btnupdate,salirupdate,menuPdate;
    TextView id;
    String user;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos_admin);

        requestQueue= Volley.newRequestQueue(this);
        edtIdadminUpdate=findViewById(R.id.edtidAdminEdit);
        edtNombreUpdate=findViewById(R.id.edtNombreEdit);
        edtApellidoUpdate=findViewById(R.id.edtApellidoEdit);
        edtUsuarioUpdate = findViewById(R.id.edtUserEdit);
        edtClaveUpdate = findViewById(R.id.edtPassEdit);
        edtFechaNacUpdate=findViewById(R.id.edtFechaNacEdit);
        edtDireccionUpdate=findViewById(R.id.edtDireccionEdit);
        edtTelUpdate=findViewById(R.id.edtTelEdit);
        btnupdate=findViewById(R.id.btnAdminEdit);
       salirupdate=findViewById(R.id.btnSalirEdit);
        menuPdate=findViewById(R.id.btn_menu);
        id=findViewById(R.id.textView28);

        Bundle bundle=getIntent().getExtras();

        if(bundle!=null){
            user=bundle.getString("user");


        }
        veradmin();
        btnupdate.setOnClickListener(this::onClick);
        menuPdate.setOnClickListener(this::onClick);
        salirupdate.setOnClickListener(this::onClick);


    }
    public void onClick(View v){

        int id = v.getId() ;
        if(id==R.id.btnAdminEdit){

            String idadmin=edtIdadminUpdate.getText().toString().trim();
            String nomadmin=edtNombreUpdate.getText().toString().trim();
            String apadmin=edtApellidoUpdate.getText().toString().trim();
            String diradmin=edtDireccionUpdate.getText().toString().trim();
            String clavadmin=edtClaveUpdate.getText().toString().trim();
            String fechaadmin=edtFechaNacUpdate.getText().toString().trim();
            String teladmin= edtTelUpdate.getText().toString().trim();

            editaradmin(idadmin,nomadmin,apadmin,diradmin,clavadmin,fechaadmin,teladmin);
            GuardarPreferencias();
        }else if(id==R.id.btn_menu){
            Intent intent = new Intent(EditarDatosAdmin.this, menuAdmin.class);
            startActivity(intent);
            GuardarPreferencias();
        }if(id==R.id.btnSalirEdit){

            finish();
        }


    }
     // guardo el id del admin
    public void GuardarPreferencias(){
        SharedPreferences preferences= getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String id_admin= edtIdadminUpdate.getText().toString();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("id_admin_fk",id_admin);
        id.setText(id_admin);

        editor.commit();



    }


    //Funcion de editar

    public void editaradmin(String idadmin,String nomadmin, String apadmin,String diradmin,String clavadamin,String fechaadmin, String teladmin){
        String URL="http://192.168.1.3/hos/EditarAdmin.php";
        StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarDatosAdmin.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
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
                parametros.put("idadmin",idadmin);
                parametros.put("nomadmin",nomadmin);
                parametros.put("apadmin",apadmin);
                parametros.put("diradmin",diradmin);
                parametros.put("user",user);
                parametros.put("clavadmin",clavadamin);
                parametros.put("fechaadmin",fechaadmin);
                parametros.put("teladamin",teladmin);
                return parametros;
            }
        };
        requestQueue.add(stringRequest);

    }








    //ver cursos
    private void veradmin(){
        String URL="http://192.168.1.3/hos/MostrarAdmin.php?user="+user;
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String cod, nom, apell,dir,usua,clav,fechan,tele;
                try {
                    cod = response.getString("id_admin");
                    nom = response.getString("nombre");
                    apell = response.getString("apellido");
                    dir= response.getString("direccion");
                    usua = response.getString("usuario");
                    clav= response.getString("clave");
                    fechan = response.getString("fecha_nac");
                    tele= response.getString("telefono");

                    edtIdadminUpdate.setText(cod);
                    edtNombreUpdate.setText(nom);
                    edtApellidoUpdate.setText(apell);
                    edtDireccionUpdate.setText(dir);
                    edtUsuarioUpdate.setText(usua);
                    edtClaveUpdate.setText(clav);
                    edtFechaNacUpdate.setText(fechan);
                    edtTelUpdate.setText(tele);

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