package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.entity.UsuarioDTO;
import br.com.locaweb.request.UsuarioRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UsuarioMediatorService  implements  UsuarioMediator {

    private final UsuarioFindService usuarioFindService;
    private final UsuarioCreateService usuarioCreateService;
    private final UsuarioDeleteService usuarioDeleteService;
    private final UsuarioUpdateService usuarioUpdateService;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioFindService.getUsuarios();
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioCreateService.create(usuario);
    }

    @Override
    public Usuario delete(String id) {
        return usuarioDeleteService.delete(id);
    }

    @Override
    public Usuario update(UsuarioRequest usuario, String id) {
        return usuarioUpdateService.update(usuario, id);
    }

    @Override
    public Usuario getUsuarioById(String id) {
        return usuarioFindService.getUsuarioById(id);
    }

    @Override
    public UserDetails getUsuarioByUserName(String userName) {
        return (Usuario) usuarioFindService.getUsuarioByUserName(userName);
    }

    @Override
    public UsuarioDTO getTemaUsuario(String userName) {
        return usuarioFindService.getTema(userName);
    }

    @Override
    public Usuario updateTema(UsuarioDTO usuario, String id) {
        return usuarioUpdateService.updateTema(usuario, id);
    }
}
