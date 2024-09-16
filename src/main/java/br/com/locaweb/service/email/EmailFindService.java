package br.com.locaweb.service.email;

import br.com.locaweb.entity.Email;
import br.com.locaweb.mapper.EmailMapper;
import br.com.locaweb.repository.EmailRepository;
import br.com.locaweb.response.EmailResponse;
import br.com.locaweb.search.EmailSearch;
import br.com.locaweb.specification.EmailSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailFindService {
    private final EmailSpecification specification;
    private final EmailRepository emailRepository;
    private final EmailMapper mapper;
    private final MongoTemplate mongoTemplate;

    @Transactional
    public List<Email> getEmails() {
        return emailRepository.findAll();
    }

    @Transactional
    Email getEmailById(String id) {
        return emailRepository.findById(id).get();
    }

    @Transactional
    public Page<EmailResponse> getEmailsByUserId(String userId, Pageable pageable, EmailSearch search) {
        // Constrói a query com base nos critérios definidos no specification
        Query query = new Query();
        List<Email> criteriaList = specification.searchEmails(search, userId);

        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        // Aplica paginação à consulta
        query.with(pageable);

        // Obtém os resultados paginados
        List<Email> emails = mongoTemplate.find(query, Email.class);

        // Conta o total de registros (necessário para a paginação)
        long total = mongoTemplate.count(query, Email.class);

        // Converte o resultado para EmailResponse e retorna a página
        List<EmailResponse> emailResponses = emails.stream()
                .map(mapper::emailToEmailResponse)
                .toList();

        return new PageImpl<>(emailResponses, pageable, total);
    }
}
