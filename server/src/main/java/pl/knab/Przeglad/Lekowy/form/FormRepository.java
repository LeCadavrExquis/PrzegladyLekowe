package pl.knab.Przeglad.Lekowy.form;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FormRepository extends MongoRepository<FormEntity, String>{
    
    //returns basic info of all forms avaiable for user
    List<FormBasicInfo> findByUserEmail(String userEmail);

    //returns basic info of all forms assaigned by doctor
    List<FormBasicInfo> findByDoctorEmail(String doctorEmail);
 
    @Query("{doctorEmail : ?0, status : 'COMPLETED'}")
    List<FormBasicInfo> findCompletedForDoctor(String doctorEmail);

    @Query("{doctorEmail : ?0, status : 'UNCOMPLETED'}")
    List<FormBasicInfo> findUncompletedForDoctor(String doctorEmail);

    @Query("{userEmail : ?0, status : 'COMPLETED'}")
    List<FormBasicInfo> findCompletedForUser(String userEmail);

    @Query("{userEmail : ?0, status : 'UNCOMPLETED'}")
    List<FormBasicInfo> findUncompletedForUser(String userEmail);

    @Query("{_id :?0}")
    FormEntity findForm(String id);


    
}
