package br.com.locaweb.service;

import br.com.locaweb.dto.Usuario;

import java.util.List;

public interface UsuarioMediator {

    List<Usuario> getUsuarios();
    Usuario save(Usuario usuario);
    void delete(Integer id);

}
