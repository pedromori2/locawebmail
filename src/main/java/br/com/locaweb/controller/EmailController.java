package br.com.locaweb.controller;

import br.com.locaweb.entity.Email;
import br.com.locaweb.service.email.EmailMediator;
import br.com.locaweb.util.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailMediator emailMediator;

    @Autowired
    private RateLimiter rateLimiter;

    private static final int MAX_DESTINATARIOS = 15;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Email> getEmails() {
        return emailMediator.getEmails();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert")
    public Email criarEmail(@RequestBody Email email) {

        int totalDestinatarios = (email.getEmail_para() != null ? email.getEmail_para().size() : 0)
                + (email.getEmail_cc() != null ? email.getEmail_cc().size() : 0)
                + (email.getEmail_cco() != null ? email.getEmail_cco().size() : 0);

        if (totalDestinatarios > MAX_DESTINATARIOS) {
            throw new IllegalArgumentException("AntiSpam Policy: Too Many Recipients. The total number of recipients cannot exceed " + MAX_DESTINATARIOS);
        }

        if (!rateLimiter.canSendEmail(email.getUser_id())) {
            throw new IllegalStateException("AntiSpam Policy: You cannot send more than 2 emails per second. Please wait and try again.");
        }

        return emailMediator.create(email);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Email deletarEmail(@PathVariable String id) {
        return emailMediator.delete(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{id}")
    public Email getEmailById(@PathVariable String id) {
        return emailMediator.getEmailById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    public Page<Email> getEmailsByUserId(@PathVariable("user_id") final String userId,
                                         @PathVariable final Integer page,
                                         @PathVariable final Integer size) {

        return emailMediator.getEmailsByUserId(userId, page, size);
    }

}
