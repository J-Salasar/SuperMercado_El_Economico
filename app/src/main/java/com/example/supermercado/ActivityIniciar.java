package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ActivityIniciar extends AppCompatActivity {
public static final String usuarios = "user";
public TextView bienvendio;
public String usuariologueado;
public BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        bienvendio=(TextView) findViewById(R.id.txtBienvendioUser);
        usuariologueado=getIntent().getStringExtra("user");
        bienvendio.setText(usuariologueado);

        //bottomNavigationView=(BottomNavigationView) findViewById(R.id.boton_de_navegacion);
        //getSupportFragmentManager().beginTransaction().replace(R.id.containerMain.);



    }

    public void miPerfil(View view)
    {
        startActivity(new Intent(ActivityIniciar.this,ActivityPerfilUsuario.class));
    }

    public void Integrantes(View view)
    {
        startActivity(new Intent(ActivityIniciar.this,Integrantes.class));
    }




}