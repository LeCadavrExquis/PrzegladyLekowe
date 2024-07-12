package pl.knab.Przeglad.Lekowy.form;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FormRepository extends MongoRepository<FormEntity, String>{
    
    //returns basic info of all forms avaiable for user
    List<FormBasicInfo> findByUserEmail(String userEmail);

    //returns basic info of all forms assaigned by doctor
    List<FormBasicInfo> findByDoctorEmail(String doctorEmail);

    //Optional<FormEntity> findById(String id); autoprovided
    
}
