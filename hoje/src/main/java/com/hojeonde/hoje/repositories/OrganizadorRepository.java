package com.hojeonde.hoje.repositories;

import com.hojeonde.hoje.models.Organizador;
import com.hojeonde.hoje.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizadorRepository extends JpaRepository<Organizador, String> {
}
