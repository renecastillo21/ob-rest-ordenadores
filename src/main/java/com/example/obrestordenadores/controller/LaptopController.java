package com.example.obrestordenadores.controller;

import com.example.obrestordenadores.entities.Laptop;
import com.example.obrestordenadores.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {


    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    //atributos
    private LaptopRepository laptopRepository;

    //constructores


    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //CRUD sobre la entidad Laptop

    //Listar lista de libros
    @GetMapping("/api/laptops")
    public List<Laptop> findall() {
        return laptopRepository.findAll(); //devolver todos los libros
    }


    /**
     * Buscar un solo libro por ID
     *
     * @param laptop by ID
     * @return
     */

    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave tipo Long") @PathVariable Long id) {

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        //Opcion 1
        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());

        else
            return ResponseEntity.notFound().build();


    }

    /**
     * Crear una nueva entrada de laptop en base de datos
     *
     * @param laptop
     * @return
     */

    @PostMapping("/api/newlaptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        //guardar el objeto recibido en la base de datos.
        if (laptop.getId() != null) {
            log.warn("trying to create a laptop with id");
            System.out.println("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);

    }


    /**
     * UPDATE LAPTOP BY ID
     */

    @PutMapping("/api/laptops/")
    public ResponseEntity<Laptop> updateLaptop(@RequestBody Laptop laptop) {
        if (laptop.getId() == null) {
            log.warn("trying to update a non existent laptop");
            System.out.println("trying to update a non existent laptop");
            return ResponseEntity.badRequest().build();
        }

        if (!laptopRepository.existsById(laptop.getId())) {
            log.warn("trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete Laptop by ID
     * @param id
     * @return
     */
    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> deletelaptop(@PathVariable("id") long id) {
        if (!laptopRepository.existsById(id)) {
            log.warn("trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        } else {
            laptopRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

    }


    /**
     * Delete all laptops from database
     */

    @ApiIgnore
    @DeleteMapping("/api/laptops/")

    public ResponseEntity<Laptop> deleteAll(){
    log.info("REST Request for deliting all laptops");
    laptopRepository.deleteAll();
    return ResponseEntity.noContent().build();

    }


}