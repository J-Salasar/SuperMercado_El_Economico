package com.example.supermercado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
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
    private static final int REQUEST_CODE=1;
    private String currentPhotoPath,entrada,entrada1;
    private int validar_dato=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        init();

    }

    public void init()
    {
        nombre=(EditText) findViewById(R.id.txtRNombre) ;
        apellido=(EditText) findViewById(R.id.txtRApellido);
        telefono=(EditText) findViewById(R.id.txtRTelefono);
        correo=(EditText) findViewById(R.id.txtRCorreo);
        password=(EditText) findViewById(R.id.txtRClave);
        usuario=(EditText) findViewById(R.id.txtRUsuario);
        imgUser=(ImageView) findViewById(R.id.imgRUsers);
        imgVolver=(ImageView) findViewById(R.id.imgVolver) ;
        guardar=(Button) findViewById(R.id.btnRRegistrar);
        imgVolver.setOnClickListener(this::onClickBack);
        currentPhotoPath = getIntent().getStringExtra("foto");
        byte[] bytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            bytes = Base64.getDecoder().decode(currentPhotoPath);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        imgUser.setImageBitmap(bitmap);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar_campos();
            }
        });
    }

    private void onClickBack(View view) {
        startActivity(new Intent(ActivityRegistrar.this,ActivityIniciar.class));
        finish();
    }

    public boolean verificar(String dato,int numero){
        String opcion1="[A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion2="[A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion3="[A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion4="[A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}[ ][A-Z,Ñ][a-z,ñ]{2,20}";
        String opcion5="[0-9]{8,10}";
        String opcion6="[0-9,_,.,-]{4,200}";
        switch(numero){
            case 1:{
                return dato.matches(opcion1+"|"+opcion2+"|"+opcion3+"|"+opcion4);
            }
            case 2:{
                return dato.matches(opcion5);
            }
            case 3:{
                return dato.matches(opcion6);
            }
            case 4:{
                return dato.matches(opcion6);
            }
            default:{
                return false;
            }
        }
    }

    public void verificar_campos() {
        if (verificar(nombre.getText().toString().trim(), validar_dato)) {
            validar_dato = 2;
            if (verificar(apellido.getText().toString().trim(), validar_dato)) {
                validar_dato = 3;
                if (verificar(telefono.getText().toString().trim(), validar_dato)) {
                    validar_dato = 4;
                    if (verificar(correo.getText().toString().trim(), validar_dato)) {
                        validar_dato = 5;
                        if (verificar(password.getText().toString().trim(), validar_dato)) {
                            validar_dato = 6;
                            if (verificar(usuario.getText().toString().trim(), validar_dato)) {
                                validar_dato = 7;
                                if (currentPhotoPath != null) {
                                    validar_dato = 1;
                                    insertar();
                                } else {
                                    Toast.makeText(this, "Imagen no valido", Toast.LENGTH_SHORT).show();
                                    validar_dato=1;
                                }
                            } else {
                                Toast.makeText(this, "Contraseña no valido", Toast.LENGTH_SHORT).show();
                                validar_dato=1;
                            }
                        } else {
                            Toast.makeText(this, "Correo no valido", Toast.LENGTH_SHORT).show();
                            validar_dato=1;
                        }
                    } else {
                        Toast.makeText(this, "Telefono no valido", Toast.LENGTH_SHORT).show();
                        validar_dato=1;
                    }
                } else {
                    Toast.makeText(this, "Apellido no valido", Toast.LENGTH_SHORT).show();
                    validar_dato=1;
                }
            } else {
                Toast.makeText(this, "Nombre no valido", Toast.LENGTH_SHORT).show();
                validar_dato=1;

            }
        }
    }

    public void insertar(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/registrar_usuario2.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Guardado exitoso", Toast.LENGTH_SHORT).show();
                nombre.setText("");
                apellido.setText("");
                telefono.setText("");
                correo.setText("");
                password.setText("");
                usuario.setText("");
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

    //Agrega la foto al cuadro
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTTAKEFOTO && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgUser.setImageBitmap(imageBitmap);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bytes = stream.toByteArray();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                currentPhotoPath = Base64.getEncoder().encodeToString(bytes);
            }
           // entrada1="1";
        }
    }

    public void dispatchTakePictureIntent()
    {
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(takePictureIntent, REQUESTTAKEFOTO);
        }
    }

    public void permisos_camara(View view) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUESTCODECAMARA);
        } else {
            dispatchTakePictureIntent();
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCODECAMARA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(getApplicationContext(), "Permiso Denegado", Toast.LENGTH_LONG).show();
            }
        }
    }





    }

