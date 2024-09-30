package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.Ingressos;
import com.hojeonde.hoje.repositories.IngressosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngressosService {
    @Autowired
    private IngressosRepository ingressosRepository;

    public List<Ingressos> findAll() {
        return ingressosRepository.findAll();
    }

    public Ingressos findById(String id) {
        return ingressosRepository.findById(id).orElse(null);
    }

    public Ingressos save(Ingressos ingresso) {
        return ingressosRepository.save(ingresso);
    }

    public void delete(String id) {
        ingressosRepository.deleteById(id);
    }
}

