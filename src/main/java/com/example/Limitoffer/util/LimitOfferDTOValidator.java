package com.example.Limitoffer.util;

import com.example.Limitoffer.dto.LimitOfferDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class LimitOfferDTOValidator implements ConstraintValidator<ValidateLimitOfferDTO, LimitOfferDTO> {

    @Override
    public void initialize(ValidateLimitOfferDTO constraintAnnotation) {
    }

    @Override
    public boolean isValid(LimitOfferDTO limitOfferDTO, ConstraintValidatorContext context) {
        if (limitOfferDTO == null) {
            return true; // Let other validations handle null objects
        }

        // Implement your custom validation logic here for all fields
        boolean isValid = true;

        //providing default value to OfferActivationTime
        if (limitOfferDTO.getNewLimit() == null) {
            limitOfferDTO.setOfferActivationTime(LocalDateTime.now());
        }

        //Validating Expiry Time is greater than current Time and ActivationTime
        if ((!limitOfferDTO.getOfferExpiryTime().isAfter(limitOfferDTO.getOfferActivationTime()))||(!limitOfferDTO.getOfferExpiryTime().isAfter(LocalDateTime.now()))) {
            isValid = false;
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Expiry time must be greater than activation time and current time")
                    .addPropertyNode("OfferExpiryTime")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
