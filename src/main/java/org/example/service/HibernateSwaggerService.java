package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.example.model.Persoa;

import java.util.List;

@Service
    public class HibernateSwaggerService {

        @Autowired
        private RestTemplate restTemplate;

        private static final String BASE_URL = "http://localhost:8081/base";

        public Persoa crearPersoa(Persoa persoa) {
            try {
                String url = BASE_URL + "/persoa";
                HttpEntity<Persoa> requestEntity = new HttpEntity<>(persoa); // Corpo da solicitude (Persoa)

                ResponseEntity<Persoa> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Persoa.class);

                return response.getBody();
            } catch (HttpClientErrorException e) {
                System.out.println("Error ao chamar ao endpoint: " + e.getMessage());
                return null;
            }
        }
        public String saudarSwagger() {
            try {

                String url = BASE_URL + "/saudo";

                ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);

                return response.getBody();
            } catch (Exception e) {

                System.out.println("Erro ao chamar o endpoint /saudo: " + e.getMessage());
                return "Erro ao saudar";
            }
        }

        // Borra la persona con el id pasado por parámetro
        // Devuelve un string con el mensaje de borrado
        // borra con el servicio de postgres
        public String borrarPersoa(String id) {
            try {
                String url = BASE_URL + "/persoa/{id}";
                HttpEntity<String> requestEntity2 = new HttpEntity<>(id); // Corpo da solicitude (Persoa)

                ResponseEntity<String> response2 = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity2, String.class, id);

                return "persona borrada";
                //return response.getBody();
            } catch (HttpClientErrorException e) {
                System.out.println("Error ao chamar ao endpoint: " + e.getMessage());
                return null;
            }
        }
        // Obtiene la personas
        // Devuelve el response con la lista de personas
        public List<Persoa> obterPersoasSwagger() {
            try {
                String url = BASE_URL + "/persoas";
                ResponseEntity<List<Persoa>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Persoa>>() {});

                return response.getBody();
            } catch (HttpClientErrorException e) {
                System.out.println("Error ao chamar ao endpoint: " + e.getMessage());
                return null;
            }
        }



    }


