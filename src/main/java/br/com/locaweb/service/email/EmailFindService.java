package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.entity.EmailDTO;
import br.com.locaweb.entity.Usuario;
import br.com.locaweb.repository.EmailRepository;
import br.com.locaweb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailFindService {
    private final EmailRepository emailRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public List<Email> getEmails() {
        return emailRepository.findAll();
    }

    @Transactional
    Email getEmailById(String id) {
        return emailRepository.findById(id).get();
    }

    @Transactional
    public List<Email> getEmailsByUserId(ObjectId user_id) {
        return emailRepository.findByUserId(user_id);
    }

    public List<EmailDTO> searchEmails(String search) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Usuario user = (Usuario) usuarioRepository.findByUserName(userName);

        String userId = user.getId();


        ObjectId objectId;
        try {
            objectId = new ObjectId(userId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do usuário inválido.");
        }

        List<Email> emails = this.getEmailsByUserId(objectId);

        List<Email> filteredEmails = emails.stream()
                .filter(email -> email.getTitulo().toLowerCase().contains(search.toLowerCase()) ||
                        email.getConteudo().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toList());

        return filteredEmails.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmailDTO convertToDTO(Email email) {
        return new EmailDTO(email.getId(), email.getTitulo(), email.getConteudo(), email.getUserId());
    }

}
