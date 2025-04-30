package com.hojeonde.hoje.services;

import com.hojeonde.hoje.models.User;
import com.hojeonde.hoje.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> findById(Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> userOptional = userRepository.findById(String.valueOf(id));
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<User> save(User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            validarUser(user);
            User userSalvo = userRepository.save(user);
            return new ResponseEntity<>(userSalvo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST, null);
        }
    }

    public ResponseEntity<Void> delete(Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!userRepository.existsById(String.valueOf(id))) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(String.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validarUser(User user) {
        if (StringUtils.isEmpty(user.getNome())) {
            throw new IllegalArgumentException("Por gentileza, informe o nome do usuário.");
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new IllegalArgumentException("Por favor, especifique o email do usuário.");
        }
        if (!user.getEmail().contains("@")) {
            throw new IllegalArgumentException("O email do usuário parece inválido. Por favor, verifique.");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            throw new IllegalArgumentException("Gentilmente, informe a senha do usuário.");
        }
    }
}
