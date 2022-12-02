package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ActivityPago extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        retornar();
    }

    private void retornar() {
        Intent intent=new Intent(this,ActivityVerCarrito.class);
        intent.putExtra("user",getIntent().getStringExtra("user"));
        intent.putExtra("rango",getIntent().getStringExtra("rango"));
        intent.putExtra("dinero",getIntent().getStringExtra("dinero"));
        startActivity(intent);
    }
}