package pl.knab.Przeglad.Lekowy.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class FormService{

    @Autowired
    private FormRepository formRepository;

    //DOCTOR
    public List<FormBasicInfo> getFormsByDoctor(String doctorEmail){

        return formRepository.findByDoctorEmail(doctorEmail);
    }

    //DOCTOR
    public void assaignForm(FormEntity formEntity){
        formEntity.setStatus(FormStatus.UNCOMPLETED); 
        formRepository.save(formEntity);
    }

    //DOCTOR
    public List<FormBasicInfo> getCompletedForDoctor(String doctorEmail){
        return formRepository.findCompletedForDoctor(doctorEmail);
    }

    //DOCTOR
    public List<FormBasicInfo> getUncompletedForDoctor(String doctorEmail){
        return formRepository.findUncompletedForDoctor(doctorEmail);
    }

    //USER
    public List<FormBasicInfo> getFormsForUser(String userEmail){

        return formRepository.findByUserEmail(userEmail);

    }

    //USER
    public List<FormBasicInfo> getUncompletedForUser(String userEmail){
        return formRepository.findUncompletedForUser(userEmail);
    }

    //USER
    public List<FormBasicInfo> getCompletedForUser(String userEmail){
        return formRepository.findCompletedForUser(userEmail);
    }

    //USER
    public void submitForm(FormEntity formEntity){
        formEntity.setStatus(FormStatus.COMPLETED);
        formEntity.setTimeCompleted(Instant.now());
        formRepository.save(formEntity);
    }

    //USER, DOCTOR      //TODO FORM LOGIC NOT TO ALLOW USER TO ACCESS NOT HIS FORM
    public FormEntity getForm(String id){
        try{
            return formRepository.findForm(id);
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }

        return new FormEntity();
    }







}