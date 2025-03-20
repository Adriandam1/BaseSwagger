package org.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.example.model.Persoa;

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

        // mio en proceso
        public Persoa borrarPersoa(Persoa persoa, Persoa id) {
            try {
                String url = BASE_URL + "/persoa/{id}";
                HttpEntity<Persoa> requestEntity = new HttpEntity<>(persoa); // Corpo da solicitude (Persoa)

                ResponseEntity<Persoa> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Persoa.class);

                return response.getBody();
            } catch (HttpClientErrorException e) {
                System.out.println("Error ao chamar ao endpoint: " + e.getMessage());
                return null;
            }
        }


    }


