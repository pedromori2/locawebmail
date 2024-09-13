package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
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

    @Override
    public List<Email> getEmails() {
        return emailFindService.getEmails();
    }

    @Override
    public Email create(Email email) {
        return emailCreateService.create(email);
    }

    @Override
    public Email delete(String id) {
        return emailDeleteService.delete(id);
    }

    @Override
    public Email getEmailById(String id) {
        return emailFindService.getEmailById(id);
    }

    @Override
    public List<Email> getEmailsByUserId(ObjectId user_id) {
        return emailFindService.getEmailsByUserId(user_id);
    }
}
