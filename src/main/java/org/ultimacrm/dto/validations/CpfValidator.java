package org.ultimacrm.dto.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.ultimacrm.repositories.ClienteRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCpf, String> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ValidCpf constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        return !cpfExistsInDatabase(cpf);
    }

    private boolean cpfExistsInDatabase(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

}


