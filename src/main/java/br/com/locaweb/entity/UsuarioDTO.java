package br.com.locaweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    private String id;
    private String name;
    private String lastName;
    private String userName;
    private boolean tema_escuro;
}
