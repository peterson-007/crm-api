package org.ultimacrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ultimacrm.dto.ContatoDTO;
import org.ultimacrm.models.Contato;
import org.ultimacrm.repositories.ContatoRepository;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    @Autowired
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Transactional
    public void salvarContato(ContatoDTO contatoDTO) {
        validarEmailUnico(contatoDTO.getEmail());

        Contato contato = new Contato();
        contato.setTelefone(contatoDTO.getTelefone());
        contato.setEmail(contatoDTO.getEmail());

        contatoRepository.save(contato);
    }

    private void validarEmailUnico(String email) {
        if (contatoRepository.existsByEmail(email)) {
            throw new RuntimeException("E-mail já cadastrado");
        }
    }

}