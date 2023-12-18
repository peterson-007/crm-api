package org.ultimacrm.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entrega")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entregador")
    private String entregador;

    @Column(name = "recebedor")
    private String recebedor;

    @Column(name = "qt_tentativas_entrega")
    private int qtTentativasEntrega;

    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_entrega")
    private StatusEntrega status;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;


    // Enum para representar os diferentes estados de entrega
    public enum StatusEntrega {
        PENDENTE,
        EM_TRANSITO,
        ENTREGUE,
        DEVOLVIDA
    }
}
