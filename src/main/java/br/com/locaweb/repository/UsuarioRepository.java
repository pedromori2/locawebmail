package br.com.locaweb.repository;

import br.com.locaweb.dto.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

}

