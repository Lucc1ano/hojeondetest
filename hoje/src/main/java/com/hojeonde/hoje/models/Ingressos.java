package com.hojeonde.hoje.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table (name = "Ingressos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingressos {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id_ingresso;
    private String Tipo;
    private String valor;
    private String quantidade;
    private String lote;
}
