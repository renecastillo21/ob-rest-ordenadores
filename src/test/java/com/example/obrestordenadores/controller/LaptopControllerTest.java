package com.example.obrestordenadores.controller;

import com.example.obrestordenadores.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void saluda() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/hola",String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola como estas?", response.getBody());
    }

    @Test
    void findall() {
        ResponseEntity<Laptop[]>response =testRestTemplate.getForEntity("/api/laptops",Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @DisplayName("FindOneById")
    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/books/100",Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                    {
                         "marca": "Lenovo",
                        "modelo": "Ideapad Gaming",
                        "serie": "Lenovo Ideapad Gaming 3i 15",
                        "color": "Gris",
                        "precio": 829.99
                    }      
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/newlaptop", HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

           assertEquals(HttpStatus.OK, response.getStatusCode());
           assertEquals(200, response.getStatusCodeValue());
           assertEquals("Lenovo",result.getMarca());
    }

    @Test
    void updateLaptop() {
        long id = 1L;

        Laptop laptop = new Laptop(null,"Lenovo","Ideapad X55","RTX550","BLUE",725.99);
        Laptop result = new Laptop(null,"Lenovo","Ideapad X55","RX 550","BLUE",599.99);


//        void shouldUpdateTutorial() throws Exception {
//            long id = 1L;
//
//            Tutorial tutorial = new Tutorial(id, "Spring Boot @WebMvcTest", "Description", false);
//            Tutorial updatedtutorial = new Tutorial(id, "Updated", "Updated", true);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": "4",
                    "marca": "Lenovo",
                    "modelo": "Ideapad Gaming update postman",
                    "serie": "Lenovo Ideapad Gaming 3i 15",
                    "color": "Gris",
                    "precio": 829.99
                }    
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Lenovo",result.getMarca());



    }

    @Test
    void deletelaptop() {
    }

    @Test
    void deleteAll() {
    }
}