package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioUpdateService {

    private final UsuarioRepository usuarioRepository;

    public Usuario update(Usuario usuario, String id) {
        Usuario user = usuarioRepository.findById(id).get();
        user.setName(usuario.getName());
        user.setPassword(usuario.getPassword());
        user.setEmail(usuario.getEmail());
        user.setTema_escuro(usuario.isTema_escuro());
        user.setLastName(usuario.getLastName());

        usuarioRepository.save(user);

        return user;
    }
}
