package org.ultimacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ultimacrm.dto.request.PedidoRequest;
import org.ultimacrm.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarPedido(@RequestBody PedidoRequest pedidoRequest) {
        try {
            //chama o serviço para processar o pedido
            pedidoService.cadastrarPedido(pedidoRequest);

            // Retorna uma resposta de sucesso
            return new ResponseEntity<>("Pedido cadastrado com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            // Em caso de falha, retorna uma resposta de erro
            return new ResponseEntity<>("Erro ao cadastrar pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
