package br.com.locaweb.mapper;

import br.com.locaweb.entity.Email;
import br.com.locaweb.response.EmailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmailMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "caixaEmail_id", target = "caixaEmail_id")
    @Mapping(source = "user_id", target = "user_id")
    @Mapping(source = "email_de", target = "email_de")
    @Mapping(source = "email_para", target = "email_para")
    @Mapping(source = "email_cc", target = "email_cc")
    @Mapping(source = "email_cco", target = "email_cco")
    @Mapping(source = "horario", target = "horario")
    @Mapping(source = "titulo", target = "titulo")
    @Mapping(source = "conteudo", target = "conteudo")
    @Mapping(source = "foto", target = "foto")
    @Mapping(source = "anexo", target = "anexo")
    EmailResponse emailToEmailResponse(Email email);

}
