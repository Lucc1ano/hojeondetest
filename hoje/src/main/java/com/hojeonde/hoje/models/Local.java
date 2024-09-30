package com.hojeonde.hoje.models;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table (name = "Local")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Local {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private String id_Local;
    private String nome;
    private String endereço;
    private String capacidade;
}
