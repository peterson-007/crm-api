package org.ultimacrm.dto.validations;

import lombok.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCpf {

    String message() default "CPF já cadastrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

