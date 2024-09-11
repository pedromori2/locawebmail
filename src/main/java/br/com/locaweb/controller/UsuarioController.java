package br.com.locaweb.controller;

import br.com.locaweb.dto.Usuario;
import br.com.locaweb.service.UsuarioMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioMediator usuarioMediator;

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
