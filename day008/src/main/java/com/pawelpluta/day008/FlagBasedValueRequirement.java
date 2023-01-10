package com.pawelpluta.day008;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.RECORD_COMPONENT;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE, RECORD_COMPONENT})
@Retention(RUNTIME)
@Constraint(validatedBy = FlagBasedValueRequirementValidator.class)
@Documented
@interface FlagBasedValueRequirement {
    String message() default "Text must be provided when flag is true";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
