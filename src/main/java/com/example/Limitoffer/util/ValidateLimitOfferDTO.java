package com.example.Limitoffer.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import com.example.Limitoffer.util.LimitOfferDTOValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LimitOfferDTOValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateLimitOfferDTO {
    String message() default "Validation failed for one or more fields";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
