package br.com.locaweb.entity;

import br.com.locaweb.enums.CaixaEmailEnum;
import br.com.locaweb.util.conversion.ObjectIdDeserializer;
import br.com.locaweb.util.conversion.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "email")
public class Email {

        @Id
        private String id;
        private CaixaEmailEnum caixaEmail_id;
        @Field(name = "user_id")
        @JsonSerialize(using = ObjectIdSerializer.class)
        @JsonDeserialize(using = ObjectIdDeserializer.class)
        private ObjectId user_id;
        private String email_de;
        private List<String> email_para;
        private List<String> email_cc;
        private List<String> email_cco;
        private Date horario;
        private String titulo;
        private String conteudo;
        private String foto;
        private List<String> anexo;

}


