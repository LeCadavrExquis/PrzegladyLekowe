package pl.knab.Przeglad.Lekowy.template;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TemplateRepository extends MongoRepository<TemplateEntity, String>{
    
    //returns List of basic info for all templates
    List<TemplateBasicInfo> findAllBy();

    //return chosen template by id
    //Optional<FormEntity> findById(String id); autoprovided

    //add template to a db 
    //save(TemplateEntity templateEntity); autoprovided
    
}
