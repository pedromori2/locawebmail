package br.com.locaweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class UsuarioDTO {
    @Id
    private String id;
    private String name;
    private String lastName;
    private String userName;
    private boolean tema_escuro;
}
