package br.com.locaweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class Usuario {

        @Id
        private String id;
        private String name;
        private String lastName;
        private String email;
        private String password;
        private boolean tema_escuro;

}


