package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.entity.EmailDTO;
import br.com.locaweb.request.EmailRequest;
import org.bson.types.ObjectId;

import java.util.List;

public interface EmailMediator {

    List<Email> getEmails();

    List<Email> getEmailsByUserId(ObjectId user_id);

    Email create(EmailRequest emailRequest);

    Email delete(String id);

    Email update(Email email, String id);

    Email getEmailById(String id);

    List<EmailDTO> searchEmails(String search);


}
