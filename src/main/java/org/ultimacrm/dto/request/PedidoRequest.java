package org.ultimacrm.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {

    private String cpf;
    private List<Long> idsProdutos;

}
