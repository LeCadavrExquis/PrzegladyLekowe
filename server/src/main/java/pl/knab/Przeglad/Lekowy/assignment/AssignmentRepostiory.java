package pl.knab.Przeglad.Lekowy.assignment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepostiory extends MongoRepository<AssignmentEntity, String> {

   // returns basic info of all forms avaiable for user
   List<AssignmentBasicInfo> findByUserEmail(String userEmail);

   // returns basic info of all forms assaigned by doctor
   List<AssignmentBasicInfo> findByDoctorEmail(String doctorEmail);

   @Query("{doctorEmail : ?0, status : 'COMPLETED'}")
   List<AssignmentBasicInfo> findCompletedForDoctor(String doctorEmail);

   @Query("{doctorEmail : ?0, status : 'UNCOMPLETED'}")
   List<AssignmentBasicInfo> findUncompletedForDoctor(String doctorEmail);

   @Query("{userEmail : ?0, status : 'COMPLETED'}")
   List<AssignmentBasicInfo> findCompletedForUser(String userEmail);

   @Query("{userEmail : ?0, status : 'UNCOMPLETED'}")
   List<AssignmentBasicInfo> findUncompletedForUser(String userEmail);

   @Query("{ userEmail: ?0}")
   List<AssignmentBasicInfo> findForUser(String userEmail);

   @Query("{ doctorEmail: ?0}")
   List<AssignmentBasicInfo> findForDoctor(String userEmail);

   @Query("{_id :?0}")
   AssignmentEntity findAssignment(String id);
}
