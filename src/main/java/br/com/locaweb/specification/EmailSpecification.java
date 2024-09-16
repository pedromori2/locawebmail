package br.com.locaweb.specification;

import br.com.locaweb.entity.Email;
import br.com.locaweb.search.EmailSearch;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailSpecification {

    private final MongoTemplate mongoTemplate;

    public EmailSpecification(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Email> searchEmails(EmailSearch search, String userId) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();

        // Filtro por userId
        if (userId != null) {
            criteriaList.add(Criteria.where("user_id").is(userId));
        }

        // Filtro por searchEmail (conteúdo ou título)
        if (search.getSearchEmail() != null && !search.getSearchEmail().isEmpty()) {
            String searchText = search.getSearchEmail().toLowerCase();
            criteriaList.add(new Criteria().orOperator(
                    Criteria.where("conteudo").regex(searchText, "i"), // "i" para case insensitive
                    Criteria.where("titulo").regex(searchText, "i")
            ));
        }

        // Adicionando os critérios à query
        if (!criteriaList.isEmpty()) {
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }

        // Executa a consulta e retorna a lista de emails
        return mongoTemplate.find(query, Email.class);
    }
}

