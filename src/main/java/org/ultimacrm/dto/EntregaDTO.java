package org.ultimacrm.dto;

public class EntregaDTO {

    private String entregador;
    private String recebedor;
    private int qtTentativasEntrega;
    private String dataEntrega;
    private StatusEntrega status;
    private int idPedido;

    // Enum para representar os diferentes estados de entrega
    public enum StatusEntrega {
        PENDENTE,
        EM_TRANSITO,
        ENTREGUE,
        DEVOLVIDA;
    }
}
