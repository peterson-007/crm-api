package org.ultimacrm.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ultimacrm.dto.ClienteDTO;
import org.ultimacrm.service.ClienteService;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.salvarCliente(clienteDTO);
            return new ResponseEntity<>("Cliente salvo com sucesso", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.getClienteById(id);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @GetMapping("/findByCpf/{cpf}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable String cpf) {
        ClienteDTO clienteDTO = clienteService.getClienteByCpf(cpf);
        return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
    }

    @PostMapping("/salvar-com-contato-e-endereco")
    public ResponseEntity<String> salvarClienteComContatoEEndereco(@RequestBody ClienteDTO clienteDTO) {
        try {
            clienteService.salvarClienteComContatoEEndereco(clienteDTO);
            return new ResponseEntity<>("Cliente salvo com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar cliente: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}