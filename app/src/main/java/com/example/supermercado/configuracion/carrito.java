package com.example.supermercado.configuracion;

public class carrito {
    private String id,nombre,precio,cantidad,foto,cantidad_actual;
    public carrito(){

    }
    public carrito(String id, String nombre, String precio, String cantidad, String foto, String cantidad_actual){
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.cantidad=cantidad;
        this.foto=foto;
        this.cantidad_actual=cantidad_actual;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public String getCantidad_actual() {
        return cantidad_actual;
    }

    public void setCantidad_actual(String cantidad_actual) {
        this.cantidad_actual = cantidad_actual;
    }
}