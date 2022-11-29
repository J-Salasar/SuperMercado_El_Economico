package com.example.supermercado;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.supermercado.Admin.ActivityAdministrador;
import com.example.supermercado.configuracion.validar_sesion;
import com.example.supermercado.configuracion.validar_usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    private EditText usuario, clave;
    private int ocultar_spinner=1;
    private Spinner spinner1;
    private CheckBox recordar_clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.usuario);
        clave = (EditText) findViewById(R.id.clave);
        recordar_clave=(CheckBox) findViewById(R.id.recordar);
        SharedPreferences preferencias1=getSharedPreferences("usuario", Context.MODE_PRIVATE);
        usuario.setText(preferencias1.getString("usuario",""));
        SharedPreferences preferencias2=getSharedPreferences("clave", Context.MODE_PRIVATE);
        clave.setText(preferencias2.getString("clave",""));
        spinner1 = (Spinner) findViewById(R.id.spinner);
        String[] opciones = {"Seleccione","Recuperación de usuario", "Restablecer contraseña"};
        ArrayAdapter<String> adactador = new ArrayAdapter<String>(this, R.layout.spinner_estilo, opciones);
        spinner1.setAdapter(adactador);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selegir = spinner1.getSelectedItem().toString();
                switch (selegir) {
                    case "Recuperación de usuario": {
                        Intent paginarecuperar=new Intent(getApplicationContext(),ActivityRecuperar.class);
                        startActivity(paginarecuperar);
                    }
                    break;
                    case "Restablecer contraseña":{
                        Intent paginarestablecer=new Intent(getApplicationContext(),ActivityRestablecer.class);
                        startActivity(paginarestablecer);
                    }
                    break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void pagina(){
        Intent menu_principal=new Intent(getApplicationContext(),ActivityIniciar.class);
        menu_principal.putExtra("user",usuario.getText().toString().toLowerCase());
        SharedPreferences prefe=getSharedPreferences("usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor O_editor=prefe.edit();
        O_editor.putString("usuario",usuario.getText().toString());
        O_editor.commit();
        if(recordar_clave.isChecked()==true){
            SharedPreferences prefe1=getSharedPreferences("clave",Context.MODE_PRIVATE);
            SharedPreferences.Editor O_editor1=prefe1.edit();
            O_editor1.putString("clave",clave.getText().toString());
            O_editor1.commit();
        }
        usuario.setText("");
        clave.setText("");
        startActivity(menu_principal);
    }
    public void pagina2(){
        Intent menu_principal=new Intent(getApplicationContext(),ActivityReenvioCorreo.class);
        menu_principal.putExtra("user",usuario.getText().toString().toLowerCase());
        SharedPreferences prefe=getSharedPreferences("usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor O_editor=prefe.edit();
        O_editor.putString("usuario",usuario.getText().toString());
        O_editor.commit();
        if(recordar_clave.isChecked()==true){
            SharedPreferences prefe1=getSharedPreferences("clave",Context.MODE_PRIVATE);
            SharedPreferences.Editor O_editor1=prefe1.edit();
            O_editor1.putString("clave",clave.getText().toString());
            O_editor1.commit();
        }
        usuario.setText("");
        clave.setText("");
        startActivity(menu_principal);
    }
    public void pagina3(){
        Intent menu_principal=new Intent(getApplicationContext(), ActivityAdministrador.class);
        menu_principal.putExtra("user",usuario.getText().toString().toLowerCase());
        SharedPreferences prefe=getSharedPreferences("usuario",Context.MODE_PRIVATE);
        SharedPreferences.Editor O_editor=prefe.edit();
        O_editor.putString("usuario",usuario.getText().toString());
        O_editor.commit();
        if(recordar_clave.isChecked()==true){
            SharedPreferences prefe1=getSharedPreferences("clave",Context.MODE_PRIVATE);
            SharedPreferences.Editor O_editor1=prefe1.edit();
            O_editor1.putString("clave",clave.getText().toString());
            O_editor1.commit();
        }
        usuario.setText("");
        clave.setText("");
        startActivity(menu_principal);
    }
    public void iniciar_sesion(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://apk.salasar.xyz:25565/iniciar_sesion.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray contactoarray=jsonObject.getJSONArray("usuario");
                    validar_sesion cuenta=null;
                    for(int i=0;i<contactoarray.length();i++){
                        JSONObject rowcontacto=contactoarray.getJSONObject(i);
                        cuenta=new validar_sesion(
                                rowcontacto.getString("validar"),
                                rowcontacto.getString("estado"),
                                rowcontacto.getString("rango")
                        );
                    }
                    if(cuenta.getValidar().equals("aprobado")){
                        if(cuenta.getEstado().equals("Activo")){
                            if(cuenta.getRango().equals("Usuario")){
                                pagina();
                            }
                            else{
                                if(cuenta.getRango().equals("Administrador")){
                                    pagina3();
                                }
                                else {
                                    if (cuenta.getRango().equals("Repartidor")) {
                                    }
                                    else {
                                        if (cuenta.getRango().equals("Fundador")) {
                                        }
                                    }
                                }
                            }
                        }
                        else{
                            if(cuenta.getEstado().equals("Desactivado")){
                                pagina2();
                            }
                            else{
                                if(cuenta.getEstado().equals("Bloqueado")){
                                }
                            }
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Usuario y contraseña erroneos",Toast.LENGTH_LONG).show();
                    }
                }
                catch (Throwable error){
                    Toast.makeText(getApplicationContext(), "Usuario y contraseña erroneos", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("usuario", usuario.getText().toString());
                parametros.put("clave", clave.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void recuperar(View view){
        switch (ocultar_spinner){
            case 1:{
                spinner1.setVisibility(View.VISIBLE);
                ocultar_spinner=2;
            }
            break;
            case 2:{
                spinner1.setVisibility(View.INVISIBLE);
                ocultar_spinner=1;
            }
            break;
            default:
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==event.KEYCODE_BACK) {
            finishAffinity();
        }
        return super.onKeyDown(keyCode,event);
    }
    public void registrar(View view){
        Intent registro=new Intent(this,ActivityRegistrar.class);
        startActivity(registro);
    }
}