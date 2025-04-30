package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.Organizador;
import com.hojeonde.hoje.repositories.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizadorService {

    @Autowired
    private OrganizadorRepository organizadorRepository;

    public ResponseEntity<List<Organizador>> findAll() {
        List<Organizador> organizadores = organizadorRepository.findAll();
        if (organizadores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(organizadores, HttpStatus.OK);
    }

    public ResponseEntity<Organizador> findById(UUID id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Organizador> organizadorOptional = organizadorRepository.findById(id);
        return organizadorOptional.map(organizador -> new ResponseEntity<>(organizador, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Organizador> save(Organizador organizador) {
        if (organizador == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            validarOrganizador(organizador);
            Organizador organizadorSalvo = organizadorRepository.save(organizador);
            return new ResponseEntity<>(organizadorSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, null);
        }
    }

    public ResponseEntity<Void> delete(UUID id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!organizadorRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        organizadorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validarOrganizador(Organizador organizador) {
        if (StringUtils.isEmpty(organizador.getNome())) {
            throw new IllegalArgumentException("Por gentileza, informe o nome do organizador.");
        }
        if (StringUtils.isEmpty(organizador.getEmail())) {
            throw new IllegalArgumentException("Por favor, especifique o email do organizador.");
        }
        if (!organizador.getEmail().contains("@")) {
            throw new IllegalArgumentException("O email do organizador parece inválido. Por favor, verifique.");
        }
        if (StringUtils.isEmpty(organizador.getTelefone())) {
            throw new IllegalArgumentException("Gentilmente, informe o telefone do organizador.");
        }
    
    }
}
