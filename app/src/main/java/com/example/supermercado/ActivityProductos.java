package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class ActivityProductos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
    }


    public void onClickRegresar(View view)
    {
        startActivity(new Intent(ActivityProductos.this,ActivityIniciar.class));
        finish();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode== KeyEvent.KEYCODE_BACK) {
            finishAffinity();
        }
        return super.onKeyDown(keyCode,event);
    }
}