package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityIniciar extends AppCompatActivity {
public static final String usuarios = "user";
public TextView bienvendio;
public String usuariologueado;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar);

        bienvendio=(TextView) findViewById(R.id.txtBienvendioUser);
        usuariologueado=getIntent().getStringExtra("user");
        bienvendio.setText(usuariologueado);
    }
}