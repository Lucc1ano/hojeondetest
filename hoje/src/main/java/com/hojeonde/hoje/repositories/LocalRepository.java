package com.hojeonde.hoje.repositories;

import com.hojeonde.hoje.models.Local;
import com.hojeonde.hoje.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local,String> {
}
