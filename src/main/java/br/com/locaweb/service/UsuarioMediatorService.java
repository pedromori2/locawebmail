package br.com.locaweb.service;

import br.com.locaweb.dto.Usuario;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class UsuarioMediatorService  implements  UsuarioMediator {

    private final UsuarioFindService usuarioFindService;
    private final UsuarioCreateService usuarioCreateService;
    private final UsuarioDeleteService usuarioDeleteService;
    @Override
    public List<Usuario> getUsuarios() {
       return usuarioFindService.findAll();
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioCreateService.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        usuarioDeleteService.delete(id);
    }
}
