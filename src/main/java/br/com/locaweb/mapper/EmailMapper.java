package br.com.locaweb.mapper;

import br.com.locaweb.entity.Email;
import br.com.locaweb.request.EmailRequest;

public class EmailMapper {

    public static Email toEntity(EmailRequest request) {
        Email email = new Email();
        email.setCaixaEmail_id(request.getCaixaEmail_id());
        email.setUser_id(request.getUser_id());
        email.setEmail_de(request.getEmail_de());
        email.setEmail_para(request.getEmail_para());
        email.setEmail_cc(request.getEmail_cc());
        email.setEmail_cco(request.getEmail_cco());
        email.setHorario(request.getHorario());
        email.setTitulo(request.getTitulo());
        email.setConteudo(request.getConteudo());
        email.setFoto(request.getFoto());
        email.setAnexo(request.getAnexo());
        return email;
    }

}
