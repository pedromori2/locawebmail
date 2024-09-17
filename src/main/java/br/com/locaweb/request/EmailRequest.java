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
    @Field(name = "caixaEmail_id")
    private CaixaEmailEnum caixaEmailId;

    @NotNull
    @Field(name = "user_id")
    private ObjectId userId;

    @NotBlank
    @Field(name = "email_de")
    private String emailDe;

    @NotEmpty
    @Email
    @Field(name = "email_para")
    private List<String> emailPara;

    @NotEmpty
    @Email
    @Field(name = "email_cc")
    private List<String> emailCc;

    @Email
    @NotEmpty
    @Field(name = "email_cco")
    private List<String> emailCco;

    @NotNull(message = "'horario' must be a date and is required")
    private Date horario;

    @NotBlank(message = "'titulo' must be a string and is required")
    private String titulo;

    @NotBlank(message = "'conteudo' must be a string and is required")
    private String conteudo;

    private String foto;

    private List<String> anexo;
}
