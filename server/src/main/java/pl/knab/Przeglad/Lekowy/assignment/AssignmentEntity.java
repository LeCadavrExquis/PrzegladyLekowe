package pl.knab.Przeglad.Lekowy.assignment;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "assignment")
public class AssignmentEntity {
    
    @Id
    public String id;
    public String name;
    private AssignmentStatus status;
    private String doctorEmail;
    private String userEmail;
    private Object form;
    private Object anwser;
    private Instant timeCreated;
    private Instant timeCompleted;

    public AssignmentEntity() {}

    public AssignmentEntity(String name, String doctorEmail, String userEmail, Object form){
        this.name = name;
        this.doctorEmail = doctorEmail;
        this.userEmail = userEmail;
        this.form = form;
        this.timeCreated = Instant.now();
        this.status = AssignmentStatus.UNCOMPLETED;
        this.anwser = null;
        this.timeCompleted = null;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Object getForm() {
        return form;
    }

    public void setForm(Object form) {
        this.form = form;
    }

    public Object getAnwser() {
        return anwser;
    }

    public void setAnwser(Object anwser) {
        this.anwser = anwser;
    }

    public Instant getTimeCreated() {
        return timeCreated;
    }

    public AssignmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssignmentStatus status) {
        this.status = status;
    }

    public Instant getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(Instant timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

}