package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.response.EmailResponse;
import br.com.locaweb.search.EmailSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmailMediator {

    List<Email> getEmails();

    Page<EmailResponse> getEmailsByUserId(String userId, Pageable pageable, EmailSearch search);

    Email create(Email email);

    Email delete(String id);

    Email getEmailById(String id);


}
