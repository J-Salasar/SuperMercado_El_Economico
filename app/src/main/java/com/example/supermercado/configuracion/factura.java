package com.example.supermercado.configuracion;

public class factura {
    private String id,nombre,fecha,hora,latitud,longitud,telefono,total;
    public factura(){

    }
    public factura(String id,String nombre,String fecha,String hora,String latitud,String longitud,String telefono,String total ){
        this.id=id;
        this.nombre=nombre;
        this.fecha=fecha;
        this.hora=hora;
        this.latitud=latitud;
        this.longitud=longitud;
        this.telefono=telefono;
        this.total=total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
