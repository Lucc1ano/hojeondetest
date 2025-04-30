package com.hojeonde.hoje.controllers;

import com.hojeonde.hoje.models.Organizador;
import com.hojeonde.hoje.services.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping
    public ResponseEntity<List<Organizador>> listarOrganizadores() {
        return organizadorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> buscarOrganizadorPorId(@PathVariable UUID id) {
        return organizadorService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Organizador> criarOrganizador(@RequestBody Organizador organizador) {
        return organizadorService.save(organizador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarOrganizador(@PathVariable UUID id) {
        return organizadorService.delete(id);
    }
}
