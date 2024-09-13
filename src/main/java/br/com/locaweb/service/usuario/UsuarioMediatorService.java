package br.com.locaweb.service.usuario;

import br.com.locaweb.entity.Usuario;
import lombok.RequiredArgsConstructor;
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
    public Usuario update(Usuario usuario, String id) {
        return usuarioUpdateService.update(usuario, id);
    }

    @Override
    public Usuario getUsuarioById(String id) {
        return usuarioFindService.getUsuarioById(id);
    }
}
