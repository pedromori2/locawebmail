package br.com.locaweb.service;

import br.com.locaweb.dto.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioFindService {

    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

}
