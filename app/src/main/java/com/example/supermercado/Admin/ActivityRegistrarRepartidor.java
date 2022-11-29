package com.example.supermercado.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.supermercado.R;

import java.util.ArrayList;

public class ActivityRegistrarRepartidor extends AppCompatActivity {
public Spinner spinerRoles;
private ArrayList<String> datosSpiner = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_repartidor);

        spinerRoles=(Spinner) findViewById(R.id.spinnerRolusuario);
        datosSpiner.add("Repartidor");
        datosSpiner.add("Administrador");
        datosSpiner.add("Usuario");
        spinerRoles.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datosSpiner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerRoles.setAdapter(adapter);

    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getSelectedItem();
        switch (adapterView.getId())
        {

            case R.id.spinnerRolusuario:
                //Hacer algo aquí
                //Toast.makeText(cont,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

   /* @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/

public void volvermenuAdmin(View view)
{
    startActivity(new Intent(this, ActivityAdministrador.class));
    finish();
}



}