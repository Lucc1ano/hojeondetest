package com.hojeonde.hoje.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table (name = "evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Evento {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private UUID id_evento;
    private String name;
    private String descriçao;
    private String data_inicio;
    private String data_fim;
    private String id_local;
    private String id_organizador;
}
