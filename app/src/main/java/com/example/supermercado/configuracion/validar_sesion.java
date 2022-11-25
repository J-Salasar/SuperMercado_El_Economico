package com.example.supermercado.configuracion;

public class validar_sesion {
    private String validar,estado;
    public validar_sesion(){
    }
    public validar_sesion(String validar,String estado) {
        this.validar=validar;
        this.estado=estado;
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
}
