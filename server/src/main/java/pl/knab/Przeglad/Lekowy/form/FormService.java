package pl.knab.Przeglad.Lekowy.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
        formRepository.save(formEntity);
    }

    //USER
    public List<FormBasicInfo> getFormsForUser(String userEmail){

        return formRepository.findByUserEmail(userEmail);

    }

    //USER
    public void submitForm(FormEntity formEntity){
        formEntity.setStatus(FormStatus.COMPLETED);
        formEntity.setTimeCompleted(Instant.now());
    }

    //USER, DOCTOR
    public FormEntity getForm(String id){
        Optional<FormEntity> optionalForm = formRepository.findById(id);
        FormEntity formEntity = optionalForm.orElseThrow(() -> new RuntimeException("No Form with that id (that shouldn't be possible in that case)"));
        return formEntity;
    }





}