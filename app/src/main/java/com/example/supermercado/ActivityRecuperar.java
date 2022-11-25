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

    public boolean validar(String dato,int numero){
        String opcion1="[a-z,0-9]{3,50}[@][a-z,0-9]{3,50}[.][a-z]{2,10}";
        String opcion2="[a-z,0-9]{3,50}[.][a-z,0-9]{3,50}[@][a-z,0-9]{3,50}[.][a-z]{2,10}";
        String opcion3="[a-z,0-9]{3,50}[.][a-z,0-9]{3,50}[.][a-z,0-9]{3,50}[@][a-z,0-9]{3,50}[.][a-z]{2,10}";
        String opcion4="[0-9]{6}";
        switch(numero){
            case 1:{
                return dato.matches(opcion1+"|"+opcion2+"|"+opcion3);
            }
            case 2:{
                return dato.matches(opcion4);
            }
            default:{
                return false;
            }
        }
    }

}