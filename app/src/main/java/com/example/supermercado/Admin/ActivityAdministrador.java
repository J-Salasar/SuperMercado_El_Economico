package com.example.supermercado.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.supermercado.R;

public class ActivityAdministrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }


    public void onClickProducto(View view)
    {
       /* startActivity();*/
    }
    public void onClickRepartidor(View view)
    {
        startActivity(new Intent(ActivityAdministrador.this,ActivityRegistrarRepartidor.class));
    }

    public void onClickNuevoInentario(View view)
    {
        startActivity(new Intent(ActivityAdministrador.this,ActivityNuevoInventario.class));
    }
}