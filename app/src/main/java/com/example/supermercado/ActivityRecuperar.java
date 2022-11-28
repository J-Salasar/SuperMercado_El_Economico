package com.example.supermercado;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado.configuracion.validar_usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class ActivityRecuperar extends AppCompatActivity {
    private EditText correo,codigo;
    private TextView usuario;
    public boolean validar(String dato,int numero){
        String opcion1="[a-z,0-9]{3,50}[@][a-z,0-9]{3,50}[.][a-z]{2,10}";
        String opcion2="[a-z,0-9]{3,50}[.][a-z,0-9]{3,50}[@][a-z,0-9]{3,50}[.][a-z]{2,10}";
        String opcion3="[a-z,0-9]{3,50}[.][a-z,0-9]{3,50}[.][a-z,0-9]{3,50}[@][a-z,0-9]{3,50}[.][a-z]{2,10}";
        String opcion4="[0-9]{6}";
        switch(numero){
            case 1:{
                return dato.matches(opcion1+"|"+opcion2+"|"+opcion3);
            }
            case 2:{
                return dato.matches(opcion4);
            }
            default:{
                return false;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);
        correo=(EditText) findViewById(R.id.txtcorreo);
        codigo=(EditText) findViewById(R.id.txtcodigo);
        usuario=(TextView) findViewById(R.id.txtusuario);
    }
    public void generarcodigo(View view){
        if(validar(correo.getText().toString(),1)){
            generar_codigo();
        }
        else{
            Toast.makeText(this,"Correo no valido", Toast.LENGTH_SHORT).show();
        }
    }
    public void generar_codigo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://apk.salasar.xyz:25565/generar_codigo_usuario.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("usuario");
                    validar_usuario cuenta=null;
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        cuenta=new validar_usuario(
                                rowcontacto.getString("correo")
                        );
                    }
                    Toast.makeText(getApplicationContext(),cuenta.getValidar(),Toast.LENGTH_LONG).show();
                    if(cuenta.getValidar().equals("Enviado")){
                        Toast.makeText(getApplicationContext(),"Codigo enviado al correo",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Codigo no enviado al correo",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), "Error en el JSON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", correo.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void validarcodigo(View view){
        if(validar(correo.getText().toString(),1)){
            if(validar(codigo.getText().toString(),2)){
                validar_codigo();
            }
            else {
                Toast.makeText(this, "Codigo no valido", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this,"Correo no valido", Toast.LENGTH_SHORT).show();
        }
    }
    public void validar_codigo(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://apk.salasar.xyz:25565/validar_codigo_usuario.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("usuario");
                    validar_usuario cuenta=null;
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        cuenta=new validar_usuario(
                                rowcontacto.getString("user")
                        );
                    }
                    if(cuenta.getValidar().equals("XXXXXXXXXX")){
                        Toast.makeText(getApplicationContext(),"Codigo invalido",Toast.LENGTH_LONG).show();
                    }
                    else{
                        escribir(cuenta.getValidar());
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), "Error en el JSON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("correo", correo.getText().toString());
                parametros.put("codigo", codigo.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void escribir(String usuarioVer){
        usuario.setText(usuarioVer);
        correo.setText("");
        codigo.setText("");
    }
    public void retroceder(View view){
        usuario.setText("XXXXXXXXXX");
        correo.setText("");
        codigo.setText("");
        Intent paginainicial=new Intent(this,MainActivity.class);
        startActivity(paginainicial);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }
}