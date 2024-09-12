package br.com.locaweb.service;

import br.com.locaweb.entity.Email;
import org.bson.types.ObjectId;

import java.util.List;

public interface EmailMediator {

    List<Email> getEmails();

    List<Email> getEmailsByUserId(ObjectId user_id);

    Email create(Email email);

    Email delete(String id);

    Email getEmailById(String id);


}
