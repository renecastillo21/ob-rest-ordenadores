package com.example.obrestordenadores.entities;

import javax.persistence.*;

@Entity
@Table(name = "ordenadores")
public class Laptop {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private String serie;
    private String color;
    private double precio;



    //constructores

    public Laptop() {
    }

    public Laptop(Long id, String marca, String modelo, String serie, String color, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.serie = serie;
        this.color = color;
        this.precio = precio;
    }

    //getter and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
