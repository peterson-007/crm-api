package org.ultimacrm.dto.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "E-mail já cadastrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}