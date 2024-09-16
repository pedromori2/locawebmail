package br.com.locaweb.mapper;

import br.com.locaweb.entity.Email;
import br.com.locaweb.request.EmailRequest;

public class EmailMapper {

    public static Email toEntity(EmailRequest request) {
        Email email = new Email();
        email.setCaixaEmail_id(request.getCaixaEmailId());
        email.setUserId(request.getUserId());
        email.setEmail_de(request.getEmailDe());
        email.setEmail_para(request.getEmailPara());
        email.setEmail_cc(request.getEmailCc());
        email.setEmail_cco(request.getEmailCco());
        email.setHorario(request.getHorario());
        email.setTitulo(request.getTitulo());
        email.setConteudo(request.getConteudo());
        email.setFoto(request.getFoto());
        email.setAnexo(request.getAnexo());
        return email;
    }

}
