package org.ultimacrm.dto.validations;

import org.ultimacrm.repositories.ContatoRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private final ContatoRepository contatoRepository;

    public EmailValidator(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !contatoRepository.existsByEmail(email);
    }
}
