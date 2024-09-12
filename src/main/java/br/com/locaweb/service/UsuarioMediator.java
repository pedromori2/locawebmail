package br.com.locaweb.service;

import br.com.locaweb.entity.Usuario;

import java.util.List;

public interface UsuarioMediator {

    List<Usuario> getUsuarios();

    Usuario create(Usuario usuario);

    Usuario delete(String id);

    Usuario update(Usuario usuario, String id);


}
