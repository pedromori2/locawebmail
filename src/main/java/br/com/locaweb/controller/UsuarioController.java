package br.com.locaweb.controller;

import br.com.locaweb.dto.Usuario;
import br.com.locaweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable String id) {
        usuarioRepository.deleteById(id);
    }
}
