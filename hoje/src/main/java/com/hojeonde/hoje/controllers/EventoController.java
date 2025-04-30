package com.hojeonde.hoje.controllers;

import com.hojeonde.hoje.models.Evento;
import com.hojeonde.hoje.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        return eventoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoPorId(@PathVariable String id) {
        return eventoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        return eventoService.save(evento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable String id) {
        return eventoService.delete(id);
    }

    // Outros métodos do controller (atualizar, etc.) seguiriam o mesmo padrão
}
