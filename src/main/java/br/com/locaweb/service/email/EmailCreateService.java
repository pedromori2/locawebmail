package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmailCreateService {

    private final EmailRepository emailRepository;

    @Transactional
    public Email create(Email email) {
        return emailRepository.save(email);
    }

}

