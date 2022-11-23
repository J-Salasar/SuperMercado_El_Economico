package com.example.supermercado;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ActivitySplash extends AppCompatActivity {
    ImageView imgSplash;
    Animation animation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        imgSplash =findViewById(R.id.imageView);



        imgSplash.setAnimation(animation1);
        new Handler().postDelayed(this::run, 2000);

    }
    private void run() {
      startActivity(new Intent(ActivitySplash.this,MainActivity.class));
        finish();
    }


    @SuppressLint("ResourceType")
    private void init(){
        imgSplash = findViewById(R.id.imageView);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.scroll_down);
    }
}