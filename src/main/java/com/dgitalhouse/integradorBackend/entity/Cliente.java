package com.dgitalhouse.integradorBackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String nombre;
    @Column(length = 30, nullable = false)
    private String apellido;
    @Column(length = 60, nullable = false)
    private String email;
    @Column(length = 30, nullable = false)
    private String telefono;
    @Column(length = 30, nullable = false)
    private String direccion;

}
