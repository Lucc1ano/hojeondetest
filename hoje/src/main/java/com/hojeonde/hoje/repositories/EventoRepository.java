package com.hojeonde.hoje.repositories;

import com.hojeonde.hoje.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, String> {
}
