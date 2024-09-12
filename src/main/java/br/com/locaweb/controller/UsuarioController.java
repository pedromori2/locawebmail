package br.com.locaweb.controller;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.service.UsuarioMediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMediator usuarioMediator;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<Usuario> getUsuarios() {
        return usuarioMediator.getUsuarios();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {

        return usuarioMediator.create(usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Usuario deletarUsuario(@PathVariable String id) {
        return usuarioMediator.delete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public Usuario update(@RequestBody Usuario usuario, @PathVariable String id) {
        return usuarioMediator.update(usuario, id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioMediator.getUsuarioById(id);
    }
}
