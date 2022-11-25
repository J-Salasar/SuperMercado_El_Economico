package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ActivityRestablecer extends AppCompatActivity {
public ImageView imgRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer);

        imgRegresar=(ImageView) findViewById(R.id.imgVolver);
        imgRegresar.setOnClickListener(this::onClickVolver);
    }

    private void onClickVolver(View view) {
        startActivity(new Intent(ActivityRestablecer.this, MainActivity.class));
    }
}