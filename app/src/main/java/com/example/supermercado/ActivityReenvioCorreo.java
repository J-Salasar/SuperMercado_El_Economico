package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado.configuracion.validar_usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ActivityReenvioCorreo extends AppCompatActivity {
    private String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reenvio_correo);
        usuario=getIntent().getStringExtra("user");
        datos();
    }
    public void datos(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/reenviar_correo.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("correo");
                    validar_usuario cuenta=null;
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        cuenta=new validar_usuario(
                                rowcontacto.getString("validar")
                        );
                    }
                    if(cuenta.getValidar().equals("Enviado")){
                        Toast.makeText(getApplicationContext(),"Codigo enviado",Toast.LENGTH_LONG).show();
                        atras();
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
                parametros.put("usuario", usuario);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void atras(){
        Intent volver=new Intent(this, MainActivity.class);
        startActivity(volver);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }
}