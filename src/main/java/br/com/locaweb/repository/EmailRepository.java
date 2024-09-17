package br.com.locaweb.repository;

import br.com.locaweb.entity.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {


    @Query("{ 'user_id': ?0 }")
    List<Email> findByUserId(Object user_id);

}

