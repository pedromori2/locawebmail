package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailUpdateService {

    private final EmailRepository emailRepository;

    public Email update(Email email, String id) {
        Email emailUpdate = emailRepository.findById(id).get();
        emailUpdate.setCaixaEmail_id(email.getCaixaEmail_id());
        emailUpdate.setUserId(email.getUserId());
        emailUpdate.setEmail_de(email.getEmail_de());
        emailUpdate.setEmail_para(email.getEmail_para());
        emailUpdate.setEmail_cc(email.getEmail_cc());
        emailUpdate.setEmail_cco(email.getEmail_cco());
        emailUpdate.setHorario(email.getHorario());
        emailUpdate.setTitulo(email.getTitulo());
        emailUpdate.setConteudo(email.getConteudo());
        emailUpdate.setFoto(email.getFoto());
        emailUpdate.setAnexo(email.getAnexo());

        emailRepository.save(emailUpdate);

        return emailUpdate;
    }
}
