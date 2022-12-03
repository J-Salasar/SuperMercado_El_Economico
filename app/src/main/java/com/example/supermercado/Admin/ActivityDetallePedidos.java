package com.example.supermercado.Admin;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado.ActivityHistorialPedido;
import com.example.supermercado.R;
import com.example.supermercado.configuracion.factura;
import com.example.supermercado.configuracion.validar_sesion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActivityDetallePedidos extends AppCompatActivity {
    private String usuario,rango,idfactura;
    private TextView id,nombre,fecha,hora,total;
    private ListView lista;
    private ArrayList<validar_sesion> facturalista;
    private ArrayList<String> arreglofactura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pedidos);
        id=(TextView) findViewById(R.id.factura_numero);
        nombre=(TextView) findViewById(R.id.cliente_factura);
        fecha=(TextView) findViewById(R.id.fecha_factura);
        hora=(TextView) findViewById(R.id.hora_factura);
        total=(TextView) findViewById(R.id.total_factura);
        idfactura=getIntent().getStringExtra("id");
        usuario=getIntent().getStringExtra("user");
        rango=getIntent().getStringExtra("rango");
        id.setText("Factura No: "+idfactura);
        nombre.setText("Cliente: "+getIntent().getStringExtra("nombre"));
        fecha.setText("Fecha: "+getIntent().getStringExtra("fecha"));
        hora.setText("Hora: "+getIntent().getStringExtra("hora"));
        total.setText("Total: L. "+getIntent().getStringExtra("total"));
        lista=(ListView)findViewById(R.id.lista_factura2022);
        ObtenerLista();
    }
    private void ObtenerLista() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "http://apk.salasar.xyz:25565/historial_productos.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("producto");
                    validar_sesion comida=null;
                    facturalista=new ArrayList<validar_sesion>();
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        comida=new validar_sesion(
                                rowcontacto.getString("nombre"),
                                rowcontacto.getString("cantidad"),
                                rowcontacto.getString("precio")
                        );
                        facturalista.add(comida);
                    }
                    fllList();
                }
                catch (Throwable error){
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("id", idfactura);
                return parametros;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void fllList() {
        arreglofactura=new ArrayList<String>();
        for(int i=0;i<facturalista.size();i++){
            arreglofactura.add
                    (
                            facturalista.get(i).getValidar()+"\n"+
                                    "Cantidad: "+facturalista.get(i).getEstado()+"   L. "+facturalista.get(i).getRango()+"   L. "+String.valueOf(Double.parseDouble(facturalista.get(i).getEstado()) * Double.parseDouble(facturalista.get(i).getRango()))
            );
        }
        ArrayAdapter adp=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arreglofactura);
        lista.setAdapter(adp);
    }
    public void retroceder2022(View view){
        Intent intent = new Intent(this, ActivityHistorialPedido.class);
        intent.putExtra("user", usuario);
        intent.putExtra("rango", rango);
        startActivity(intent);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        return false;
    }
}