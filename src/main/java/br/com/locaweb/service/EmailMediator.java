package br.com.locaweb.service;

import br.com.locaweb.entity.Email;

import java.util.List;

public interface EmailMediator {

    List<Email> getEmails();

    Email create(Email email);

    Email delete(String id);

    Email getEmailById(String id);


}
