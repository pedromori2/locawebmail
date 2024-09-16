package br.com.locaweb.repository;

import br.com.locaweb.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    UserDetails findByUserName(String userName);
}

