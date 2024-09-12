package br.com.locaweb.service;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
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

}
