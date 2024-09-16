package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.mapper.EmailMapper;
import br.com.locaweb.repository.EmailRepository;
import br.com.locaweb.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailCreateService {

    private final EmailRepository emailRepository;

    @Transactional
    public Email create(EmailRequest emailRequest) {
        // Converte o EmailRequest para a entidade Email
        Email email = EmailMapper.toEntity(emailRequest);

        // Salva a entidade Email no banco de dados
        return emailRepository.save(email);
    }

}

