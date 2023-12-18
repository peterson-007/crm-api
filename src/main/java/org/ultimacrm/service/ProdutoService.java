package org.ultimacrm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ultimacrm.dto.ContatoDTO;
import org.ultimacrm.dto.ProdutoDTO;
import org.ultimacrm.models.Contato;
import org.ultimacrm.models.Produto;
import org.ultimacrm.repositories.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public void salvarProduto(ProdutoDTO produtoDTO) {
        validarNomeProdutoUnico(produtoDTO.getNome());

       Produto produto = new Produto();
       produto.setNome(produtoDTO.getNome());
       produto.setPreco(produtoDTO.getPreco());

        produtoRepository.save(produto);
    }

    private void validarNomeProdutoUnico(String nome) {
        if (produtoRepository.existsByNome(nome)) {
            throw new RuntimeException("Nomde do produto já cadastrado");
        }
    }



}
