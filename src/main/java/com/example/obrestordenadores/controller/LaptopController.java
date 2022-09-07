package com.example.obrestordenadores.controller;

import com.example.obrestordenadores.entities.Laptop;
import com.example.obrestordenadores.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LaptopController {

    //atributos
    private LaptopRepository laptopRepository;

    //constructores


    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //CRUD sobre la entidad Laptop

    //Listar lista de libros
    @GetMapping("/api/laptops")
    public List<Laptop> findall(){
        return laptopRepository.findAll(); //devolver todos los libros
    }

    //Crear una nueva entrada de laptop en base de datos
    @PostMapping("/api/newlaptop")
    public Laptop create(@RequestBody Laptop laptop) {

        return laptopRepository.save(laptop); //guardar entrada a la base de datos.

    }
}
