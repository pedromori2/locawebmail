package br.com.locaweb.controller;

import br.com.locaweb.entity.Email;
import br.com.locaweb.entity.Usuario;
import br.com.locaweb.service.email.EmailMediator;
import br.com.locaweb.util.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public Email update(@RequestBody Email email, @PathVariable String id) {
        return emailMediator.update(email, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{id}")
    public Email getEmailById(@PathVariable String id) {
        return emailMediator.getEmailById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/user/{userId}")
    public List<Email> getEmailsByUserId(@PathVariable String userId) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(userId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ObjectId format");
        }
        return emailMediator.getEmailsByUserId(objectId);
    }

}
