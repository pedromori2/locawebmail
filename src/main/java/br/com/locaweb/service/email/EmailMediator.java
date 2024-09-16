package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmailMediator {

    List<Email> getEmails();

    Page<Email> getEmailsByUserId(String userId, Integer page, Integer size);

    Email create(Email email);

    Email delete(String id);

    Email getEmailById(String id);


}
