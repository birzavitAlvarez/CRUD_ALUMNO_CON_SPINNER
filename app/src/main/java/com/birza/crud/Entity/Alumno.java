package com.birza.crud.Entity;

public class Alumno {
    //variables privadas
    private int codigo;
    private String nombre;
    private double pension;
    private String celular;
    private String distrito;

    //constructor (para reportes)
    public Alumno() {
    }
    public Alumno(int codigo, String nombre, double pension, String celular, String distrito) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pension = pension;
        this.celular = celular;
        this.distrito = distrito;
    }

    //getter and setter
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}
