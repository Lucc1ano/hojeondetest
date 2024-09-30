package com.hojeonde.hoje.repositories;

import com.hojeonde.hoje.models.Ingressos;
import com.hojeonde.hoje.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngressosRepository extends JpaRepository<Ingressos, String> {
}
