package com.martinaleksandrov.wantapet.models.dtos;

import com.martinaleksandrov.wantapet.annotation.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@NoArgsConstructor
@PasswordMatch
public class UserRegisterDto extends BaseRegistrationDetails{

    @Email
    @NotBlank(message = "Email cannot be empty!")
    private String email;

    //    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters!")
    private String password;

//    @PasswordMatch
    private String confirmPassword;
}
