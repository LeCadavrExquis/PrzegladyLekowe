package pl.knab.Przeglad.Lekowy.template;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.JsonNode;

@Document(collection = "template")
public class TemplateEntity {
    
    @Id
    public String id;
    private String name;
    private JsonNode form;

    public TemplateEntity(){

    }

    public TemplateEntity(String name, JsonNode form){
        this.name = name;
        this.form = form; 
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonNode getForm(){
        return form;
    }

    public void setForm(JsonNode form){
        this.form = form;
    }
}
