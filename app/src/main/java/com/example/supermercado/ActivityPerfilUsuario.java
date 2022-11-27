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
import android.graphics.BitmapFactory;
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

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ActivityPerfilUsuario extends AppCompatActivity {
    public EditText nombre,apellido,telefono,correo,password,usuario;
    public ImageView imgUser,imgVolver;
    public Button Actualizar;
    private String id;
    private static final int REQUESTCODECAMARA=100;
    private static final int REQUESTTAKEFOTO=101;
    private String currentPhotoPath;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        nombre=(EditText) findViewById(R.id.txtRNombreM) ;
        apellido=(EditText) findViewById(R.id.txtRApellidoM);
        telefono=(EditText) findViewById(R.id.txtRTelefonoM);
        correo=(EditText) findViewById(R.id.txtRCorreoM);
        password=(EditText) findViewById(R.id.txtRClaveM);
        usuario=(EditText) findViewById(R.id.txtRUsuarioM);
        imgUser=(ImageView) findViewById(R.id.imgRUsersM);
        Actualizar=(Button) findViewById(R.id.btnModificar);


        /*nombre.setText(getIntent().getStringExtra("nombre"));
        apellido.setText(getIntent().getStringExtra("apellido"));
        telefono.setText(getIntent().getStringExtra("telefono"));
        correo.setText(getIntent().getStringExtra("correo"));
        password.setText(getIntent().getStringExtra("clave"));
        usuario.setText(getIntent().getStringExtra("usuario"));
        id=getIntent().getStringExtra("id");*/
        Actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*verificar_campos();*/
               /* actualizar();*/
            }
        });
    }

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

    public void volverMenu(View view)
    {
        startActivity(new Intent(ActivityPerfilUsuario.this,ActivityIniciar.class));
        finish();
    }

   /* public void actualizar(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz/actualizar_usuario.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Actualizacion exitoso", Toast.LENGTH_SHORT).show();
                nombre.setEnabled(false);
                apellido.setEnabled(false);
                telefono.setText("");
                correo.setEnabled(false);;
                password.setText("");
                usuario.setEnabled(false);;
               /* Intent lista=new Intent(getApplicationContext(),ActivityLista.class);
                startActivity(lista);*/
         /*   }
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
*/

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager())!=null) {
            startActivityForResult(takePictureIntent, REQUESTTAKEFOTO);
        }
    }
    public void permisos_camara_modificar(View view) {
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