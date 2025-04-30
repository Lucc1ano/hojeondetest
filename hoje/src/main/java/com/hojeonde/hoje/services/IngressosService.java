package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.Ingressos;
import com.hojeonde.hoje.repositories.IngressosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngressosService {

    @Autowired
    private IngressosRepository ingressosRepository;

    public ResponseEntity<List<Ingressos>> findAll() {
        List<Ingressos> ingressos = ingressosRepository.findAll();
        if (ingressos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ingressos, HttpStatus.OK);
    }

    public ResponseEntity<Ingressos> findById(UUID id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Ingressos> ingressosOptional = ingressosRepository.findById(id);
        return ingressosOptional.map(ingresso -> new ResponseEntity<>(ingresso, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Ingressos> save(Ingressos ingresso) {
        if (ingresso == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            validarIngresso(ingresso);
            Ingressos ingressoSalvo = ingressosRepository.save(ingresso);
            return new ResponseEntity<>(ingressoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, null);
        }
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!ingressosRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ingressosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validarIngresso(Ingressos ingresso) {
        if (StringUtils.isEmpty(ingresso.getTipo())) {
            throw new IllegalArgumentException("Por gentileza, informe o tipo do ingresso.");
        }
        if (StringUtils.isEmpty(ingresso.getValor())) {
            throw new IllegalArgumentException("Por favor, especifique o valor do ingresso.");
        }
        try {
            java.math.BigDecimal valorIngresso
