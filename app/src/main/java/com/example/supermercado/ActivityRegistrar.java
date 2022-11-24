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
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ActivityRegistrar extends AppCompatActivity {
    public EditText nombre,apellido,telefono,correo,password,usuario;
    public ImageView imgUser;
    public Button guardar;
    private static final int REQUESTCODECAMARA=100;
    private static final int REQUESTTAKEFOTO=101;
    private static final int REQUEST_CODE=1;
    private String currentPhotoPath,entrada,entrada1;
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
        guardar=(Button) findViewById(R.id.btnRRegistrar);

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

