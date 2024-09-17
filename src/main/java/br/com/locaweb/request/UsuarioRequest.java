package br.com.locaweb.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequest {

    private String name;
    private String lastName;
    @NotNull
    @NotBlank
    private String userName;
    private String password;

}
