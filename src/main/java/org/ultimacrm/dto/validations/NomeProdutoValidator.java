package org.ultimacrm.dto.validations;

import org.ultimacrm.repositories.ProdutoRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NomeProdutoValidator implements ConstraintValidator<ValidNomeProduto, String> {

    private final ProdutoRepository produtoRepository;

    public NomeProdutoValidator(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void initialize(ValidNomeProduto constraintAnnotation) {
    }

    @Override
    public boolean isValid(String nome, ConstraintValidatorContext context) {
        return nome != null && !produtoRepository.existsByNome(nome);
    }
}
