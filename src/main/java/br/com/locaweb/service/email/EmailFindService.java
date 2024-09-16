package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailFindService {
    private final EmailRepository emailRepository;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public List<Email> getEmails() {
        return emailRepository.findAll();
    }

    @Transactional
    Email getEmailById(String id) {
        return emailRepository.findById(id).get();
    }

    public Page<Email> findEmailsByUserId(String userId, int page, int size) {
        Query query = new Query();
        query.addCriteria(Criteria.where("user_id").is(userId));
        query.with(PageRequest.of(page, size)); // Paginação
        List<Email> emails = mongoTemplate.find(query, Email.class);
        long total = mongoTemplate.count(query, Email.class);
        return new PageImpl<>(emails, PageRequest.of(page, size), total);
    }
}
