package com.hojeonde.hoje.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Organizador")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Organizador {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id_organizador;
    private String nome;
    private String email;
    private String telefone;
}
