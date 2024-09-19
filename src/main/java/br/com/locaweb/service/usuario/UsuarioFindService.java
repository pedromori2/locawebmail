package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.entity.UsuarioDTO;
import br.com.locaweb.mapper.UsuarioMapper;
import br.com.locaweb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioFindService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    Usuario getUsuarioById(String id) {
        return usuarioRepository.findById(id).get();
    }

    @Transactional
    UserDetails getUsuarioByUserName(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    @Transactional
    public UsuarioDTO getTema(String userName) {
        Usuario user = (Usuario) usuarioRepository.findByUserName(userName);
        UsuarioDTO userDTO = UsuarioMapper.toDTO(user);
        return userDTO;
    }

}
