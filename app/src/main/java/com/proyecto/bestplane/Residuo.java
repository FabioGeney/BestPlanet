package com.proyecto.bestplane;

public class Residuo {
    private String nombre;
    private String tipo;
    private int imagen;

    public Residuo(){

    }

    public Residuo(String nombre, String tipo, int imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return "Residuo{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
