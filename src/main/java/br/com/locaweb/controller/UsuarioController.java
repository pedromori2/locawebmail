package br.com.locaweb.controller;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.entity.UsuarioDTO;
import br.com.locaweb.mapper.UsuarioMapper;
import br.com.locaweb.repository.UsuarioRepository;
import br.com.locaweb.request.UsuarioRequest;
import br.com.locaweb.service.usuario.UsuarioMediator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioMediator usuarioMediator;
    private final UsuarioRepository usuarioRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<UsuarioDTO> getUsuarios() {
        List<Usuario> usuarios = usuarioMediator.getUsuarios();
        List<UsuarioDTO> usuarioDTOS = UsuarioMapper.toDTOList(usuarios);
        return usuarioDTOS;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/insert")
    public Usuario criarUsuario(@RequestBody UsuarioRequest usuario) {
        if (this.usuarioRepository.findByUserName(usuario.getUserName()) != null) {
            throw new RuntimeException("Usuário já cadastrado.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        Usuario newUser = new Usuario(usuario.getName(), usuario.getLastName(), usuario.getUserName(), encryptedPassword);

        return usuarioMediator.create(newUser);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public Usuario deletarUsuario(@PathVariable String id) {
        return usuarioMediator.delete(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/update/{id}")
    public Usuario update(@RequestBody UsuarioRequest usuarioRequest, @PathVariable String id) {
        Usuario existingUsuario = usuarioMediator.getUsuarioById(id);

        if (usuarioRequest.getName() != null) {
            existingUsuario.setName(usuarioRequest.getName());
        }
        if (usuarioRequest.getLastName() != null) {
            existingUsuario.setLastName(usuarioRequest.getLastName());
        }
        if (usuarioRequest.getUserName() != null) {
            existingUsuario.setUserName(usuarioRequest.getUserName());
        }
        if (usuarioRequest.getPassword() != null) {
            existingUsuario.setPassword(usuarioRequest.getPassword());
        }
        if (usuarioRequest.getTema_escuro() != null) {
            existingUsuario.setTema_escuro(usuarioRequest.getTema_escuro());
        }

        UsuarioRequest updatedRequest = new UsuarioRequest();
        updatedRequest.setName(existingUsuario.getName());
        updatedRequest.setLastName(existingUsuario.getLastName());
        updatedRequest.setUserName(existingUsuario.getUsername());
        updatedRequest.setPassword(existingUsuario.getPassword());
        updatedRequest.setTema_escuro(existingUsuario.isTema_escuro());

        return usuarioMediator.update(updatedRequest, id);

    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/id/{id}")
    public Usuario getUsuarioById(@PathVariable String id) {
        return usuarioMediator.getUsuarioById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/username/{userName}")
    public Usuario getUsuarioByUserName(@PathVariable String userName) {
        return (Usuario) usuarioMediator.getUsuarioByUserName(userName);
    }
}
