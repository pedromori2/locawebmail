package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.entity.EmailDTO;
import br.com.locaweb.request.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailMediatorService implements  EmailMediator {

    private final EmailFindService emailFindService;
    private final EmailCreateService emailCreateService;
    private final EmailDeleteService emailDeleteService;
    private final EmailUpdateService emailUpdateService;

    @Override
    public List<Email> getEmails() {
        return emailFindService.getEmails();
    }

    @Override
    public Email create(EmailRequest emailRequest) {
        return emailCreateService.create(emailRequest);
    }

    @Override
    public Email delete(String id) {
        return emailDeleteService.delete(id);
    }

    @Override
    public Email update(Email email, String id) {
        return emailUpdateService.update(email, id);
    }

    @Override
    public Email getEmailById(String id) {
        return emailFindService.getEmailById(id);
    }

    @Override
    public List<EmailDTO> searchEmails(String search) {
        return emailFindService.searchEmails(search);
    }

    @Override
    public List<Email> getEmailsByUserId(ObjectId user_id) {
        return emailFindService.getEmailsByUserId(user_id);
    }
}
