package br.com.locaweb.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequest {

    private String name;
    private String lastName;
    @Email
    @NotNull
    @NotBlank
    private String email;
    private String password;

}
