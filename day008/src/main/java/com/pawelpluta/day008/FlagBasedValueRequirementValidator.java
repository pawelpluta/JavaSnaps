package com.pawelpluta.day008;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class FlagBasedValueRequirementValidator implements ConstraintValidator<FlagBasedValueRequirement, SampleRequest.NestedRequestData> {
    @Override
    public void initialize(FlagBasedValueRequirement constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(SampleRequest.NestedRequestData value, ConstraintValidatorContext context) {
        if (value.magicLogicFlag()) {
            return value.magicLogicField() != null && !value.magicLogicField().isBlank();
        }
        return true;
    }
}
