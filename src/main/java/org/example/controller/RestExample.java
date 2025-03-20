package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.model.Persoa;
import org.example.repository.PersoaRepository;
import org.example.service.PersoaService;
import org.example.service.HibernateSwaggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RestExample.MAPPING)
public class RestExample {

    public static final String MAPPING = "/persoas";

    @Autowired
    private PersoaRepository persoaRepository;

    @Autowired
    PersoaService persoaService;
    @Autowired
    private HibernateSwaggerService hibernateSwagger;

    @PostMapping("/crearPersoaHibernateSwagger")
    public Persoa crearPersoaHibernateSwagger(@RequestBody Persoa persoa) {
        return hibernateSwagger.crearPersoa(persoa);
    }
    // mio en proceso
    @DeleteMapping("/borrarPersoaHibernateSwagger/{id}")
    public String borrarPersoaHibernateSwagger(@PathVariable String id) {
        hibernateSwagger.borrarPersoa(id);
        return "persoa borrada";
    }

    @PostMapping("/saudoSwagger")
    public String saudoSwagger() {

        return hibernateSwagger.saudarSwagger();
    }


    @Operation(summary = "Método que saúda")
    @PostMapping("/saudo")
    public String saudo() {
        return "Boas! parece que isto, contra todo pronostico, funciona";
    }


    @Operation(summary = "Crear unha nova persoa")
    @PostMapping("/persoa")
    public Persoa crearPersoa(@RequestBody Persoa persoa) {
        return persoaService.upsert(persoa);
    }


    @Operation(summary = "Obter todas as persoas")
    @GetMapping("/persoas")
    public List<Persoa> obterPersoas() {
        return persoaService.list();
    }

    @Operation(summary = "Obter persoa por ID")
    @GetMapping("/persoa/{id}")
    public ResponseEntity<Persoa> obterPersoaPorId(@PathVariable String id) {
        Optional<Persoa> persoa = persoaService.getById(id);
        return persoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Actualizar unha persoa")
    @PutMapping("/persoa/{id}")
    public ResponseEntity<Persoa> actualizarPersoa(@PathVariable String id, @RequestBody Persoa persoaDetails) {
        Optional<Persoa> persoaOptional = persoaService.getById(id);
        if (persoaOptional.isPresent()) {
            Persoa persoa = persoaOptional.get();
            persoa.setNome(persoaDetails.getNome());
            persoa.setIdade(persoaDetails.getIdade());
            persoa.setEmail(persoaDetails.getEmail());
            Persoa persoaActualizada = persoaService.upsert(persoa);
            return ResponseEntity.ok(persoaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Eliminar unha persoa")
    @DeleteMapping("/persoa/{id}")
    public ResponseEntity<Void> eliminarPersoa(@PathVariable String id) {
        if (persoaRepository.existsById(id)) {
            persoaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
