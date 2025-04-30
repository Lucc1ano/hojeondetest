package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.Evento;
import com.hojeonde.hoje.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public ResponseEntity<List<Evento>> findAll() {
        List<Evento> eventos = eventoRepository.findAll();
        if (eventos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    public ResponseEntity<Evento> findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Evento> eventoOptional = eventoRepository.findById(id);
        return eventoOptional.map(evento -> new ResponseEntity<>(evento, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Evento> save(Evento evento) {
        if (evento == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            validarEvento(evento);
            Evento eventoSalvo = eventoRepository.save(evento);
            return new ResponseEntity<>(eventoSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Void> delete(String id) {
        if (StringUtils.isEmpty(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!eventoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        eventoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validarEvento(Evento evento) {
        if (StringUtils.isEmpty(evento.getNome())) {
            throw new IllegalArgumentException("Por gentileza, informe o nome do evento.");
        }
        if (evento.getDataInicio() == null) {
            throw new IllegalArgumentException("Por favor, especifique a data de início do evento.");
        }
        if (evento.getDataFim() == null) {
            throw new IllegalArgumentException("Gentilmente, informe a data de término do evento.");
        }
        if (evento.getDataInicio().isAfter(evento.getDataFim())) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de término, por favor, verifique.");
        }
        if (evento.getCapacidade() == null || evento.getCapacidade() <= 0) {
            throw new IllegalArgumentException("A capacidade do evento deve ser um valor positivo, agradecemos a sua atenção.");
        }
        if (evento.getPreco() == null || evento.getPreco().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O preço do ingresso deve ser um valor não negativo, por favor, confira.");
        }
        if (StringUtils.isEmpty(evento.getLocalizacao())) {
            throw new IllegalArgumentException("A localização do evento é um campo obrigatório, por favor, forneça essa informação.");
        }
    }
}
