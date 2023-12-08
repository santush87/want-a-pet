package com.martinaleksandrov.wantapet.validation;

import com.martinaleksandrov.wantapet.annotation.PasswordMatch;
import com.martinaleksandrov.wantapet.models.dtos.binding.UserRegisterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegisterDto> {
    private String message;

    @Override
    public void initialize (PasswordMatch constraintAnnotation) {

        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid (UserRegisterDto userRegisterDto, ConstraintValidatorContext context) {

        final String password = userRegisterDto.getPassword();
        final String confirmPassword = userRegisterDto.getConfirmPassword();

        if (password == null && confirmPassword == null) {

            return true;
        } else {

            boolean passwordMatch = password != null && password.equals(confirmPassword);

            if (!passwordMatch) {

                HibernateConstraintValidatorContext hibernateContext =
                        context.unwrap(HibernateConstraintValidatorContext.class);

                hibernateContext
                        .buildConstraintViolationWithTemplate(message)
                        .addPropertyNode("confirmPassword")
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }

            return passwordMatch;
        }
    }
}
