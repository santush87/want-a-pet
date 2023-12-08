package com.martinaleksandrov.wantapet.models.dtos.binding;

import com.martinaleksandrov.wantapet.annotation.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@PasswordMatch
public class UserRegisterDto extends BaseRegistrationDetails {

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    //    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters!")
    private String password;

    @NotNull(message = "Confirm password could not be null!")
    private String confirmPassword;

    public UserRegisterDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRegisterDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRegisterDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
