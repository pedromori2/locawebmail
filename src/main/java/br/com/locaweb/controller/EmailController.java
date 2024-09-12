package br.com.locaweb.controller;

import br.com.locaweb.entity.Email;
import br.com.locaweb.service.EmailMediator;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailMediator emailMediator;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Email> getEmails() {
        return emailMediator.getEmails();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert")
    public Email criarEmail(@RequestBody Email email) {

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
    public List<Email> getEmailsByUserId(@PathVariable String userId) {
        ObjectId objectId;
        try {
            objectId = new ObjectId(userId);  // Manually convert String to ObjectId
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ObjectId format");
        }
        // Fetch emails based on the converted ObjectId
        return emailMediator.getEmailsByUserId(objectId);
    }

}
