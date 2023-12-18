package org.ultimacrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {


    private String datahoraCriacao;

    private String datahoraEntrega;

    private double valorPedido;

    private String status;

    private int idCliente;

}
