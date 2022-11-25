package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ActivityRecuperar extends AppCompatActivity {
    ImageView imgRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar);

        imgRegresar=(ImageView) findViewById(R.id.imgVolver);
        imgRegresar.setOnClickListener(this::onClickRegresar);

    }

    private void onClickRegresar(View view) {
        startActivity(new Intent(ActivityRecuperar.this,MainActivity.class));
        finish();
    }

}