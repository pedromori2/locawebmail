package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import br.com.locaweb.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioUpdateService {

    private final UsuarioRepository usuarioRepository;

    public Usuario update(UsuarioRequest usuario, String id) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        Usuario user = usuarioRepository.findById(id).get();
        user.setName(usuario.getName());
        user.setPassword(encryptedPassword);
        user.setUserName(usuario.getUserName());
        user.setTema_escuro(usuario.isTema_escuro());
        user.setLastName(usuario.getLastName());

        usuarioRepository.save(user);

        return user;
    }
}
