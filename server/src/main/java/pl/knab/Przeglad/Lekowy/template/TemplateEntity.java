package pl.knab.Przeglad.Lekowy.template;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "template")
public class TemplateEntity {
    
    @Id
    public String id;
    private String name;
    private Object form;

    public TemplateEntity(){

    }

    public TemplateEntity(String name, Object form){
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

    public Object getForm(){
        return form;
    }

    public void setForm(Object form){
        this.form = form;
    }
}
