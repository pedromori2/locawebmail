package br.com.locaweb.service;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioDeleteService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario delete(String id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);

        return usuario;
    }
}
