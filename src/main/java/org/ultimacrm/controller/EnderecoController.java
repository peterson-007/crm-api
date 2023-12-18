package org.ultimacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ultimacrm.dto.EnderecoDTO;
import org.ultimacrm.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarEndereco(@Validated @RequestBody EnderecoDTO enderecoDTO) {
        enderecoService.salvarEndereco(enderecoDTO);
        return ResponseEntity.ok().build();
    }

}
