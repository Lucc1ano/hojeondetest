package com.hojeonde.hoje.controllers;

import com.hojeonde.hoje.models.Organizador;
import com.hojeonde.hoje.services.OrganizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {
    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping
    public List<Organizador> getAllOrganizadores() {
        return organizadorService.findAll();
    }

    @GetMapping("/{id}")
    public Organizador getOrganizadorById(@PathVariable String id) {
        return organizadorService.findById(id);
    }

    @PostMapping
    public Organizador createOrganizador(@RequestBody Organizador organizador) {
        return organizadorService.save(organizador);
    }

    @DeleteMapping("/{id}")
    public void deleteOrganizador(@PathVariable String id) {
        organizadorService.delete(id);
    }
}

