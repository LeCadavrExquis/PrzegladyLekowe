package pl.knab.Przeglad.Lekowy.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepostiory assignmentRepository;

    // DOCTOR
    public void assign(String name, String doctorEmail, String userEmail, Object form) {
        AssignmentEntity assignment = new AssignmentEntity(name, doctorEmail, userEmail, form);
        assignment.setStatus(AssignmentStatus.UNCOMPLETED);
        assignmentRepository.save(assignment);
    
    }

    // USER
    public void submit(String id, Object anwser, String email) throws Exception {
        AssignmentEntity assignment = assignmentRepository.findById(id).orElse(null);
        
        if(!assignment.getUserEmail().equals(email)){
            throw new Exception("not your form to submit");
        }
        assignment.setAnwser(anwser);
        assignment.setTimeCompleted(Instant.now());
        assignment.setStatus(AssignmentStatus.COMPLETED);
        assignmentRepository.save(assignment);

    }
 
    public AssignmentEntity getAssignment(String id, String email) throws Exception {

        AssignmentEntity assignment = assignmentRepository.findAssignment(id);
        if (email.equals(assignment.getDoctorEmail()) || email.equals(assignment.getUserEmail())) {
            return assignment;
        } else {
            throw new Exception("thats not your form to see");
        }
    }

    public List<AssignmentBasicInfo> getAssignmentsFor(String email) {
        
        List<AssignmentBasicInfo> doctorList = assignmentRepository.findForDoctor(email);
        List<AssignmentBasicInfo> userList = assignmentRepository.findForUser(email);

        List<AssignmentBasicInfo> combinedList = new ArrayList<>(doctorList);
        combinedList.addAll(userList);

        return combinedList;
    }

}