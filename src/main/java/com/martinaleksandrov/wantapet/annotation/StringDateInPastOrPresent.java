package com.martinaleksandrov.wantapet.annotation;

import com.martinaleksandrov.wantapet.validation.StringDateInPastOrPresentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StringDateInPastOrPresentValidator.class)
public @interface StringDateInPastOrPresent {
    String message() default "Date is not in the past or present!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
