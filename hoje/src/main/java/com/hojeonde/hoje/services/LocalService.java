package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.Local;
import com.hojeonde.hoje.repositories.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocalService {

    @Autowired
    private LocalRepository localRepository;

    public ResponseEntity<List<Local>> findAll() {
        List<Local> locais = localRepository.findAll();
        if (locais.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(locais, HttpStatus.OK);
    }

    public ResponseEntity<Local> findById(UUID id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Local> localOptional = localRepository.findById(id);
        return localOptional.map(local -> new ResponseEntity<>(local, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Local> save(Local local) {
        if (local == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            validarLocal(local);
            Local localSalvo = localRepository.save(local);
            return new ResponseEntity<>(localSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, null);
        }
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!localRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        localRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validarLocal(Local local) {
        if (StringUtils.isEmpty(local.getNome())) {
            throw new IllegalArgumentException("Por gentileza, informe o nome do local.");
        }
        if (StringUtils.isEmpty(local.getEndereço())) {
            throw new IllegalArgumentException("Por favor, especifique o endereço do local.");
        }
        if (StringUtils.isEmpty(local.getCapacidade())) {
            throw new IllegalArgumentException("Gentilmente, informe a capacidade máxima do local.");
        }
        try {
            int capacidadeLocal = Integer.parseInt(local.getCapacidade());
            if (capacidadeLocal <= 0) {
