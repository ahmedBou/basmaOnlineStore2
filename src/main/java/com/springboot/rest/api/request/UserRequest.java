package com.springboot.rest.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {
    @NotBlank(message="firstName: Ce champ ne doit etre null !")
    @Size(min = 3, message = "Fn: Cannot be null")
    private String firstName;

    @NotBlank(message="lastName: Ce champ ne doit etre null !")
    @Size(min = 3, message = "Ln: Cannot be null")
    private String lastName;

    @NotBlank(message="Email: Ce champ ne doit etre null !")
    @Email(message="email2: ce champ doit respecter le format email !")
    private String email;

    @NotBlank(message="Password: Ce champ ne doit etre null !")
    @Size(min=8, message="mot de passe doit avoir au moins 8 caracteres !")
    @Size(max=12, message="mot de passe doit avoir au max 12 caracteres !")
    private String password;

}
