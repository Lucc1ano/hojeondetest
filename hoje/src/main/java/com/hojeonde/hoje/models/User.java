package com.hojeonde.hoje.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;

    public void setId(Long id) {

    }
}
