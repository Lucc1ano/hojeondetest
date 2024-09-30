package com.hojeonde.hoje.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table (name = "evento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Evento {
    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String descriçao;
    private String data_inicio;
    private String data_fim;
    private String local_id;
    private String organizador_id;

}
