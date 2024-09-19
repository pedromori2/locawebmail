package br.com.locaweb.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UsuarioRequest {

    private String name;
    private String lastName;
    @NotNull
    @NotBlank
    private String userName;
    private String password;
    @Setter
    @Getter
    private Boolean tema_escuro;

}
