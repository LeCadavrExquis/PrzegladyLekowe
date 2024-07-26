package pl.knab.Przeglad.Lekowy.form;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "form")
public class FormEntity {
    
    @Id
    public String id;
    private FormStatus status;
    private String doctorEmail;
    private String userEmail;
    private Object form;
    private Object anwser;
    private Instant timeCreated;
    private Instant timeCompleted;

    public FormEntity() {}

    public FormEntity(String doctorEmail, String userEmail, Object form){
        this.doctorEmail = doctorEmail;
        this.userEmail = userEmail;
        this.form = form;
        this.timeCreated = Instant.now();

        this.status = FormStatus.UNCOMPLETED;
        this.anwser = null;
        this.timeCompleted = null;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }

    public Instant getTimeCompleted() {
        return timeCompleted;
    }

    public void setTimeCompleted(Instant timeCompleted) {
        this.timeCompleted = timeCompleted;
    }

}