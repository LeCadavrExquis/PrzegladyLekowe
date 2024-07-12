package pl.knab.Przeglad.Lekowy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import pl.knab.Przeglad.Lekowy.form.FormBasicInfo;
import pl.knab.Przeglad.Lekowy.form.FormEntity;
import pl.knab.Przeglad.Lekowy.form.FormService;
import pl.knab.Przeglad.Lekowy.template.TemplateBasicInfo;
import pl.knab.Przeglad.Lekowy.template.TemplateService;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/doctor/forms")
public class DoctorController{

    @Autowired
    FormService formService;

    @Autowired
    TemplateService templateService;

    @GetMapping
    public List<FormBasicInfo> getDoctorForms(HttpServletRequest req){

        HttpSession session = req.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
        String doctorEmail = (String) securityContext.getAuthentication().getPrincipal();

        List<FormBasicInfo> resultList = formService.getFormsByDoctor(doctorEmail);
        return resultList;
    }
    @PostMapping
    public ResponseEntity<HttpStatus> assignForm(@RequestBody FormEntity assaignedForm){
        formService.assaignForm(assaignedForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public FormEntity getForm(@PathVariable String id){
        return formService.getForm(id);
    }

    @GetMapping("/templates")
    public List<TemplateBasicInfo> getTemplates(HttpServletRequest req){
        return templateService.getAllTemplatesBasicInfo();
        
    }



}