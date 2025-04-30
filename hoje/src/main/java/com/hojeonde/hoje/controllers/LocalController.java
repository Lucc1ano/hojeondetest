package com.hojeonde.hoje.controllers;

import com.hojeonde.hoje.models.Local;
import com.hojeonde.hoje.services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/locais")
public class LocalController {

    @Autowired
    private LocalService localService;

    @GetMapping
    public ResponseEntity<List<Local>> listarLocais() {
        return localService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> buscarLocalPorId(@PathVariable UUID id) {
        return localService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Local> criarLocal(@RequestBody Local local) {
        return localService.save(local);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLocal(@PathVariable UUID id) {
        return localService.delete(id);
    }
}
