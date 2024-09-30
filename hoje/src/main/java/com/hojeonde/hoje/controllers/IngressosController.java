package com.hojeonde.hoje.controllers;

import com.hojeonde.hoje.models.Ingressos;
import com.hojeonde.hoje.services.IngressosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressosController {
    @Autowired
    private IngressosService ingressosService;

    @GetMapping
    public List<Ingressos> getAllIngressos() {
        return ingressosService.findAll();
    }

    @GetMapping("/{id}")
    public Ingressos getIngressoById(@PathVariable String id) {
        return ingressosService.findById(id);
    }

    @PostMapping
    public Ingressos createIngresso(@RequestBody Ingressos ingresso) {
        return ingressosService.save(ingresso);
    }

    @DeleteMapping("/{id}")
    public void deleteIngresso(@PathVariable String id) {
        ingressosService.delete(id);
    }
}

