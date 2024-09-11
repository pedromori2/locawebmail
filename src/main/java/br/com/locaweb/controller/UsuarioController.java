package br.com.locaweb.controller;

import br.com.locaweb.dto.Usuario;
import br.com.locaweb.service.UsuarioMediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMediator usuarioMediator;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioMediator.getUsuarios();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
         return usuarioMediator.save(usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Integer id) {
        usuarioMediator.delete(id);
    }
}
