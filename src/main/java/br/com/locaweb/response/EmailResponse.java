package br.com.locaweb.response;

import br.com.locaweb.enums.CaixaEmailEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {
    private String id;
    private CaixaEmailEnum caixaEmail_id;
    private String user_id;
    private String email_de;
    private List<String> email_para;
    private List<String> email_cc;
    private List<String> email_cco;
    private Date horario;
    private String titulo;
    private String conteudo;
    private String foto;
    private List<String> anexo;
}
