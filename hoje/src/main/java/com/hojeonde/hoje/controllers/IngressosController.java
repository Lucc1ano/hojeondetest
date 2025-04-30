package com.hojeonde.hoje.controllers;

import com.hojeonde.hoje.models.Ingressos;
import com.hojeonde.hoje.services.IngressosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingressos")
public class IngressosController {

    @Autowired
    private IngressosService ingressosService;

    @GetMapping
    public ResponseEntity<List<Ingressos>> listarIngressos() {
        return ingressosService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingressos> buscarIngressoPorId(@PathVariable UUID id) {
        return ingressosService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Ingressos> criarIngresso(@RequestBody Ingressos ingresso) {
        return ingressosService.save(ingresso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIngresso(@PathVariable UUID id) {
        return ingressosService.delete(id);
    }
}
