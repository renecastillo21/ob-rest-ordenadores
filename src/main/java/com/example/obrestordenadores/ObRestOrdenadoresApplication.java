package com.example.obrestordenadores;

import com.example.obrestordenadores.entities.Laptop;
import com.example.obrestordenadores.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObRestOrdenadoresApplication {

	public static void main(String[] args) {


		ApplicationContext context = SpringApplication.run(ObRestOrdenadoresApplication.class, args);

		LaptopRepository repository = context.getBean(LaptopRepository.class);

		//Agregar laptops

		Laptop laptop1 = new Laptop(null,"ASUS","ProArt StudioBook","ASUS ProArt StudioBook 16","Black",1649.99);
		Laptop laptop2 = new Laptop(null,"SAMSUNG","Chromebook 4","Chromebook 4 11'","Titanio platino",125.46);
		Laptop laptop3 = new Laptop(null,"Apple","MacBook Air","MacBook Air 2020","Gris espacial",899.00);

		//Guardar Datos
		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);


	}

}
