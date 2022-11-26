package com.example.supermercado.configuracion;

public class validar_sesion {
    private String validar,estado,rango;
    public validar_sesion(){
    }
    public validar_sesion(String validar,String estado,String rango) {
        this.validar=validar;
        this.estado=estado;
        this.rango=rango;
    }

    public String getValidar() {
        return validar;
    }

    public void setValidar(String validar) {
        this.validar = validar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
}
