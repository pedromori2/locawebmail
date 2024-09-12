package br.com.locaweb.repository;

import br.com.locaweb.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email, String> {

}

