package org.ultimacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.ultimacrm.dto.ContatoDTO;
import org.ultimacrm.service.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ResponseEntity<Void> salvarContato(@Validated @RequestBody ContatoDTO contatoDTO) {
        contatoService.salvarContato(contatoDTO);
        return ResponseEntity.ok().build();
    }
}

