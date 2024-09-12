package br.com.locaweb.service;

import br.com.locaweb.entity.Email;
import br.com.locaweb.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailFindService {

    private final EmailRepository emailRepository;

    @Transactional
    public List<Email> getEmails() {
        return emailRepository.findAll();
    }

    @Transactional
    Email getEmailById(String id) {
        return emailRepository.findById(id).get();
    }

}
