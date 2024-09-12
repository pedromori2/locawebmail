package br.com.locaweb.repository;

import br.com.locaweb.entity.Email;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EmailRepository extends MongoRepository<Email, String> {

    @Query("{ 'user_id': ?0 }")
    List<Email> findByUserId(ObjectId user_id);

}

