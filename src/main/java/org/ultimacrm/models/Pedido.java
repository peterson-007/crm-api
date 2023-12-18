package org.ultimacrm.models;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "datahora_criacao")
    private LocalDateTime datahoraCriacao;

    @Column(name = "datahora_entrega")
    private LocalDateTime datahoraEntrega;

    @Column(name = "valor_pedido")
    private double valorPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pedido")
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToMany
    @JoinTable(name = "PedidoProduto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos;


    // Enum para representar os diferentes estados de um pedido
    //@ApiModel(description = "Status do pedido")
    public enum StatusPedido {
        AGUARDANDO_PAGAMENTO,
        EM_PROCESSAMENTO,
        ENVIADO,
        FINALIZADO,
        CANCELADO
    }
}
