package com.example.supermercado.Admin;

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

import com.example.supermercado.R;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ActivityNuevoInventario extends AppCompatActivity {
    public EditText txtNombre,TxtApellido,txtTelefono,txtCorreo,txtUsuario,txtClave;
    public ImageView imgInventario;
    public Button guardarInventario;
    private static final int REQUESTCODECAMARA=100;
    private static final int REQUESTTAKEFOTO=101;
    private String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_inventario);

        txtNombre=(EditText) findViewById(R.id.txtRNombreInventario);
        TxtApellido=(EditText) findViewById(R.id.txtRApellidoInventario);
        txtTelefono=(EditText) findViewById(R.id.txtRTelefonoInventario);
        txtCorreo=(EditText) findViewById(R.id.txtRCorreoInventario);
        txtUsuario=(EditText) findViewById(R.id.txtRUsuarioInventario);
        txtClave=(EditText) findViewById(R.id.txtRClaveInventario);
        guardarInventario=(Button) findViewById(R.id.btnRRegistrarInventario);
        imgInventario=(ImageView)findViewById(R.id.imgRInventario);
        guardarInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTTAKEFOTO && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgInventario.setImageBitmap(imageBitmap);
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

    public void permisos_camara_inventario(View view) {
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