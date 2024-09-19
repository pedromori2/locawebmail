package br.com.locaweb.mapper;

import br.com.locaweb.entity.Usuario;
import br.com.locaweb.entity.UsuarioDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getName(),
                usuario.getLastName(),
                usuario.getUsername(),
                usuario.isTema_escuro()
        );
    }

    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

}
