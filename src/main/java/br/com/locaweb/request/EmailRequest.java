package br.com.locaweb.request;

import br.com.locaweb.enums.CaixaEmailEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

@Data
public class EmailRequest {

    @NotBlank(message = "'caixaEmail_id' must be a string and is required")
    private CaixaEmailEnum caixaEmailId;

    @NotNull(message = "'user_id' must be an objectId and is required")
    private ObjectId userId;  // Considerando que objectId será tratado como String ou um tipo adequado

    @NotBlank(message = "'email_de' must be a string and is required")
    private String emailDe;

    @NotEmpty(message = "'email_para' must be an array of strings and is required")
    private List<@Email(message = "'email_para' must contain valid email addresses") String> emailPara;

    private List<@Email(message = "'email_cc' must contain valid email addresses") String> emailCc;

    private List<@Email(message = "'email_cco' must contain valid email addresses") String> emailCco;

    @NotNull(message = "'horario' must be a date and is required")
    private Date horario;

    @NotBlank(message = "'titulo' must be a string and is required")
    private String titulo;

    @NotBlank(message = "'conteudo' must be a string and is required")
    private String conteudo;

    private String foto;

    private List<String> anexo;
}
