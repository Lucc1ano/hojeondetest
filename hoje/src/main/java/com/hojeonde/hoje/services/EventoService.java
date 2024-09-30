package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.Evento;
import com.hojeonde.hoje.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    public Evento findById(String id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void delete(String id) {
        eventoRepository.deleteById(id);
    }
}

