package br.com.locaweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class EmailDTO {

    private String id;
    private String titulo;
    private String conteudo;
    private ObjectId userId;

}



