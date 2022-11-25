package com.example.supermercado;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
public class ActivityRegistrar extends AppCompatActivity {
    public EditText nombre,apellido,telefono,correo,password,usuario;
    public ImageView imgUser,imgVolver;
    public Button guardar;
    private static final int REQUESTCODECAMARA=100;
    private static final int REQUESTTAKEFOTO=101;
    private String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        nombre=(EditText) findViewById(R.id.txtRNombre) ;
        apellido=(EditText) findViewById(R.id.txtRApellido);
        telefono=(EditText) findViewById(R.id.txtRTelefono);
        correo=(EditText) findViewById(R.id.txtRCorreo);
        password=(EditText) findViewById(R.id.txtRClave);
        usuario=(EditText) findViewById(R.id.txtRUsuario);
        imgUser=(ImageView) findViewById(R.id.imgRUsers);
        imgVolver=(ImageView) findViewById(R.id.imgVolver) ;
        guardar=(Button) findViewById(R.id.btnRRegistrar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar_campos();
            }
        });
        imgVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volver();
            }
        });
    }
    public void volver() {
        Intent iniciar=new Intent(this,MainActivity.class);
        startActivity(iniciar);
    }
    public boolean verificar(String dato,int numero){
        String opcion1="[A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion2="[A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion3="[A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion4="[A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion5="[0-9]{8}";
        String opcion6="[a-z,0-9]{2,50}[@][a-z]{2,50}[.][a-z]{2,10}";
        String opcion7="[a-z,0-9]{2,50}[._][a-z,0-9]{2,50}[@][a-z]{2,50}[.][a-z]{2,10}";
        String opcion8="[a-z,0-9]{2,50}[._][a-z,0-9]{2,50}[._][a-z,0-9]{2,50}[@][a-z]{2,50}[.][a-z]{2,10}";
        String opcion9="[a-z,0-9]{2,50}[._][a-z,0-9]{2,50}[._][a-z,0-9]{2,50}[._][a-z,0-9]{2,50}[@][a-z]{2,50}[.][a-z]{2,10}";
        String opcion10="[a-z,0-9,_,-]{3,10}";
        String opcion11="[a-z,0-9,A-Z,%,&,$,#,@,!,?,¡,¿,ñ,Ñ,+,/]{8,50}";
        switch(numero){
            case 1:{
                return dato.matches(opcion1+"|"+opcion2+"|"+opcion3+"|"+opcion4);
            }
            case 2:{
                return dato.matches(opcion1+"|"+opcion2+"|"+opcion3+"|"+opcion4);
            }
            case 3:{
                return dato.matches(opcion5);
            }
            case 4:{
                return dato.matches(opcion6+"|"+opcion7+"|"+opcion8+"|"+opcion9);
            }
            case 5:{
                return dato.matches(opcion11);
            }
            case 6:{
                return dato.matches(opcion10);
            }
            default:{
                return false;
            }
        }
    }
    public void verificar_campos() {
        int validar_dato=1;
        if (verificar(nombre.getText().toString(), validar_dato)) {
            validar_dato = 2;
            if (verificar(apellido.getText().toString(), validar_dato)) {
                validar_dato = 3;
                if (verificar(telefono.getText().toString(), validar_dato)) {
                    validar_dato = 4;
                    if (verificar(correo.getText().toString(), validar_dato)) {
                        validar_dato=5;
                        if (verificar(password.getText().toString(),validar_dato)) {
                            validar_dato =6;
                            if (verificar(usuario.getText().toString(), validar_dato)) {
                                if (currentPhotoPath != null) {
                                    validar_dato = 1;
                                    validar_correo();
                                }
                                else {
                                    Toast.makeText(this, "Imagen no tomada", Toast.LENGTH_SHORT).show();
                                    validar_dato = 1;
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Usuario no valido", Toast.LENGTH_LONG).show();
                                validar_dato = 1;
                            }
                        }
                        else {
                            Toast.makeText(this, "Contraseña no valido", Toast.LENGTH_SHORT).show();
                            validar_dato = 1;
                        }
                    }
                    else {
                        Toast.makeText(this, "Correo no valido", Toast.LENGTH_SHORT).show();
                        validar_dato = 1;
                    }
                }
                else {
                    Toast.makeText(this, "Telefono no valido", Toast.LENGTH_SHORT).show();
                    validar_dato=1;
                }
            }
            else {
                Toast.makeText(this, "Apellido no valido", Toast.LENGTH_SHORT).show();
                validar_dato=1;
            }
        }
        else {
            Toast.makeText(this, "Nombre no valido", Toast.LENGTH_SHORT).show();
        }
    }
    public void validar_correo(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/validar_correo.php", new Response.Listener<String>(){
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
                    if(cuenta.getValidar().equals("aprobado")){
                        validar_usuario();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Correo existente",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("correo",correo.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void validar_usuario(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/validar_usuario.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("usuario");
                    validar_usuario cuenta=null;
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        cuenta=new validar_usuario(
                                rowcontacto.getString("validar")
                        );
                    }
                    if(cuenta.getValidar().equals("aprobado")){
                        insertar();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Usuario existente",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("usuario",usuario.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void insertar(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/registrar_usuario.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("registro");
                    validar_usuario cuenta=null;
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        cuenta=new validar_usuario(
                                rowcontacto.getString("validar")
                        );
                    }
                    if(cuenta.getValidar().equals("Registrado")){
                        Toast.makeText(getApplicationContext(), cuenta.getValidar(), Toast.LENGTH_SHORT).show();
                        correo();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error Fatal",Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("nombre",nombre.getText().toString());
                parametros.put("apellido",apellido.getText().toString());
                parametros.put("telefono",telefono.getText().toString());
                parametros.put("correo",correo.getText().toString());
                parametros.put("clave",password.getText().toString());
                parametros.put("usuario",usuario.getText().toString());
                parametros.put("foto",currentPhotoPath);
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void correo(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/correo_verificar.php", new Response.Listener<String>(){
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
                    if(cuenta.getValidar().equals("Correo de verificacion enviado")){
                        nombre.setText("");
                        apellido.setText("");
                        telefono.setText("");
                        correo.setText("");
                        password.setText("");
                        usuario.setText("");
                        volver();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error Fatal",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> parametros=new HashMap<String,String>();
                parametros.put("correo",correo.getText().toString());
                parametros.put("usuario",usuario.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    //Agrega la foto al cuadro
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTTAKEFOTO && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgUser.setImageBitmap(imageBitmap);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            currentPhotoPath = Base64.getEncoder().encodeToString(bytes);
        }
    }
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(takePictureIntent, REQUESTTAKEFOTO);
        }
    }
    public void permisos_camara(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUESTCODECAMARA);
        }
        else {
            dispatchTakePictureIntent();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODECAMARA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            }
            else {
                Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_LONG).show();
            }
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode== KeyEvent.KEYCODE_BACK) {
            finishAffinity();
        }
        return super.onKeyDown(keyCode,event);
    }
}