package br.com.locaweb.controller;

import br.com.locaweb.entity.Email;
import br.com.locaweb.entity.EmailDTO;
import br.com.locaweb.entity.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import br.com.locaweb.request.EmailRequest;
import br.com.locaweb.service.email.EmailMediator;
import br.com.locaweb.util.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailMediator emailMediator;
    private final UsuarioRepository usuarioRepository;

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
    public Email criarEmail(@RequestBody EmailRequest emailRequest) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario user = (Usuario) usuarioRepository.findByUserName(userName);
        ObjectId objectId = new ObjectId(user.getId());
        emailRequest.setUser_id(objectId);

        int totalDestinatarios = (emailRequest.getEmail_para() != null ? emailRequest.getEmail_para().size() : 0)
                + (emailRequest.getEmail_cc() != null ? emailRequest.getEmail_cc().size() : 0)
                + (emailRequest.getEmail_cco() != null ? emailRequest.getEmail_cco().size() : 0);

        if (totalDestinatarios > MAX_DESTINATARIOS) {
            throw new IllegalArgumentException("AntiSpam Policy: Too Many Recipients. The total number of recipients cannot exceed " + MAX_DESTINATARIOS);
        }

        if (!rateLimiter.canSendEmail(emailRequest.getUser_id())) {
            throw new IllegalStateException("AntiSpam Policy: You cannot send more than 2 emails per second. Please wait and try again.");
        }

        return emailMediator.create(emailRequest);
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
            objectId = new ObjectId(userId);  // Manually convert String to ObjectId
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid ObjectId format");
        }
        // Fetch emails based on the converted ObjectId
        return emailMediator.getEmailsByUserId(objectId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/search")
    public ResponseEntity<List<EmailDTO>> searchEmails(
            @RequestParam(value = "search") String search) {

        List<EmailDTO> result = emailMediator.searchEmails(search);
        return ResponseEntity.ok(result);
    }

}
