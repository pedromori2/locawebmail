package br.com.locaweb.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create("mongodb+srv://pedromori:China123@cluster0.geeg2fr.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"), "Cluster0");
    }
}






