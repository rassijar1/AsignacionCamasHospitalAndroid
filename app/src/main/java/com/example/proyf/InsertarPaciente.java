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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class InsertarPaciente extends AppCompatActivity {
    SharedPreferences preferences;
    Spinner spinner,spinner2;
    EditText edtidpac,edtnompac,edtapellidopac,edtuserpac,edtclavepac,edtfechapac, edtdireccionpac,edtpactel,edtbuscarpac, id_fk;
    Button btninsertarpaciente, btnbuscarpaciente,btnsalirpaciente;
    TextView id2; //foranea normalizacion
    TextView id_paciente_txt;
    TextView seradc;//servicio adicional
    RequestQueue requestQueue;
    //paciente_fk pac= new paciente_fk();

    EditarDatosAdmin ob= new EditarDatosAdmin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);


        setContentView(R.layout.activity_insertar_paciente);
        edtidpac=(EditText)findViewById(R.id.edtinsertPAcId);
        spinner=(Spinner)findViewById(R.id.spinner_diag);
        spinner2=(Spinner)findViewById(R.id.spinner_servAdc);
        edtnompac=(EditText)findViewById(R.id.edtInsertarPacNombre);
        edtapellidopac=(EditText)findViewById(R.id.edtInsertarPacApellido);
        edtuserpac=(EditText)findViewById(R.id.edtInsertarPacUsuario);
        edtclavepac=(EditText)findViewById(R.id.edtInsertarPacClave);
        edtfechapac=(EditText)findViewById(R.id.edtInsertarPacFecha);
        edtdireccionpac=(EditText)findViewById(R.id.edtInsertarDireccion);
        edtpactel=(EditText)findViewById(R.id.edtInsertarPacTelefono);
        edtbuscarpac=(EditText)findViewById(R.id.edtBuscarPacID);
        id2=findViewById(R.id.textView29);
        id_paciente_txt=(TextView)findViewById(R.id.txtpaciente);
        id_fk=findViewById(R.id.edtid_fk);
        seradc=findViewById(R.id.textservadicional);
        btninsertarpaciente=(Button)findViewById(R.id.btnCreatePaciente);
        String id_admin=preferences.getString("id_admin_fk",null); //foranea admin

        //String id_diagnostico="2";
        if (id_admin!=null){
            id_fk.setText(id_admin);
        }
        id_fk.setEnabled(false);
        String diagnosticos []={"digestivo","respiratorio","sistema cardiovascular","endocrinologo","sistema Nervioso"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,diagnosticos);
        spinner.setAdapter(adapter);

        String servicios_adicionales []={"Habitacion Reservada", "Enfermera personal","Hora adicional acompañante"};
        ArrayAdapter<String> adapter2= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,servicios_adicionales);
        spinner2.setAdapter(adapter2);

            //id2.setText(id_diagnostico);
        btnbuscarpaciente=(Button)findViewById(R.id.btnBuscarPaciente);
        btnsalirpaciente= findViewById(R.id.btnVolverins);

        btnsalirpaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences= getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();*/

                finish();

            }
        });



        btninsertarpaciente.setOnClickListener(this::onClick);
        btnbuscarpaciente.setOnClickListener(this::onClick);
    }


    public void onClick(View v){
        int id= v.getId();
        if(id==R.id.btnCreatePaciente){
            insertar("http://192.168.1.3/hos/InsertarPaciente.php");
            String seleccionado=spinner.getSelectedItem().toString();
            if(seleccionado.equals("Digestivo")){
                //pac.setId_diagnostico_fk("1");
                id2.setText("1");
                Toast.makeText(this.getApplicationContext(), "el"+id2, Toast.LENGTH_SHORT).show();
            }else if(seleccionado.equals("Respiratorio")){
                //pac.setId_diagnostico_fk("2");
                id2.setText("2");

            }
            else if(seleccionado.equals("sistema cardiovascular")){
                //pac.setId_diagnostico_fk("2");
                id2.setText("3");

            }else if(seleccionado.equals("endocrinologo")){
                //pac.setId_diagnostico_fk("2");
                id2.setText("4");

            }else if(seleccionado.equals("sistema Nervioso")){
                //pac.setId_diagnostico_fk("2");
                id2.setText("5");

            }
            String seleccionado2=spinner2.getSelectedItem().toString();
            if(seleccionado2.equals("Habitacion Reservada")){
                //pac.setId_diagnostico_fk("1");
                seradc.setText("1");
                Toast.makeText(this.getApplicationContext(), "el"+id2, Toast.LENGTH_SHORT).show();
            }else if(seleccionado2.equals("Enfermera personal")){
                //pac.setId_diagnostico_fk("2");
                seradc.setText("2");

            }
            else if(seleccionado2.equals("Hora adicional acompañante")){
                //pac.setId_diagnostico_fk("2");
                seradc.setText("3");

            }


            GuardarPreferencias();

        }else if(id==R.id.btnBuscarPaciente){
            buscar();
        }


    }

    private void insertar(String URL){

            //enviar los datos capturados
            StringRequest stringRequest= new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "Se registro el paciente ", Toast.LENGTH_SHORT).show();
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
                    parametros.put("id_paciente",edtidpac.getText().toString());
                    parametros.put("nombre",edtnompac.getText().toString());
                    parametros.put("apellido",edtapellidopac.getText().toString());
                    parametros.put("direccion",edtdireccionpac.getText().toString());
                    parametros.put("usuario",edtuserpac.getText().toString());
                    parametros.put("clave",edtclavepac.getText().toString());
                    parametros.put("fecha_nac",edtfechapac.getText().toString());
                    parametros.put("telefono",edtpactel.getText().toString());
                    parametros.put("id_admin_fk",id_fk.getText().toString());
                    parametros.put("id_diagnostico_fk",id2.getText().toString());
                    parametros.put("id_servicio_adicional_fk",seradc.getText().toString());


                    return parametros;
                }
            };
            requestQueue= Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);



    }

    private void buscar(){
        Intent intent= new Intent(this,EditarPaciente.class);
        intent.putExtra("id",edtbuscarpac.getText().toString().trim());
        startActivity(intent);



    }

    public void GuardarPreferencias(){
        SharedPreferences preferences= getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String id_paciente= edtidpac.getText().toString();
        SharedPreferences.Editor editor= preferences.edit();
        editor.putString("id_paciente_fk",id_paciente);
        id_paciente_txt.setText(id_paciente);

        editor.commit();



    }
}
