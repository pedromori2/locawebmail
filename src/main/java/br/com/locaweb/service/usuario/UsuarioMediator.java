package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.entity.UsuarioDTO;
import br.com.locaweb.request.UsuarioRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioMediator {

    List<Usuario> getUsuarios();

    Usuario create(Usuario usuario);

    Usuario delete(String id);

    Usuario update(UsuarioRequest usuario, String id);

    Usuario getUsuarioById(String id);

    UserDetails getUsuarioByUserName(String userName);

    UsuarioDTO getTemaUsuario(String userName);

}
