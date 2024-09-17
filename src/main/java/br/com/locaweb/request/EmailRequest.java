package br.com.locaweb.request;

import br.com.locaweb.enums.CaixaEmailEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
public class EmailRequest {

    @NotBlank
    private CaixaEmailEnum caixaEmail_id;

    @NotNull
    private ObjectId user_id;

    @NotBlank
    @Email
    private String email_de;

    @NotEmpty
    @Email
    private List<String> email_para;

    @NotEmpty
    @Email
    private List<String> email_cc;

    @Email
    @NotEmpty
    private List<String> email_cco;

    @NotNull(message = "'horario' must be a date and is required")
    private Date horario;

    @NotBlank(message = "'titulo' must be a string and is required")
    private String titulo;

    @NotBlank(message = "'conteudo' must be a string and is required")
    private String conteudo;

    private String foto;

    private List<String> anexo;
}
