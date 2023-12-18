package org.ultimacrm.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "contato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email", unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
