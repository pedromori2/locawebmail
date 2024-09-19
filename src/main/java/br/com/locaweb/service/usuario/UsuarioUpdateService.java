package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.entity.UsuarioDTO;
import br.com.locaweb.repository.UsuarioRepository;
import br.com.locaweb.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioUpdateService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;

    public Usuario update(UsuarioRequest usuario, String id) {
        Usuario user = usuarioRepository.findById(id).get();
        boolean senhaCorreta = encoder.matches(usuario.getPassword(), user.getPassword());

        if (!senhaCorreta) {
            String encryptedPassword = encoder.encode(usuario.getPassword());
            user.setPassword(encryptedPassword);
        }

        user.setName(usuario.getName());
        user.setUserName(usuario.getUserName());
        user.setTema_escuro(usuario.getTema_escuro());
        user.setLastName(usuario.getLastName());

        usuarioRepository.save(user);

        return user;
    }

    @Transactional
    public Usuario updateTema(UsuarioDTO usuario, String id) {
        Usuario user = usuarioRepository.findById(id).get();

        user.setLastName(usuario.getLastName());
        user.setUserName(usuario.getUserName());
        user.setTema_escuro(usuario.isTema_escuro());

        usuarioRepository.save(user);

        return user;
    }

}
