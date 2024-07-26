package pl.knab.Przeglad.Lekowy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/patient/forms")
public class PatientController {

    @Autowired
    FormService formService;

    private String extractEmailFromSecurityContext(HttpServletRequest req) {

        HttpSession session = req.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute(SPRING_SECURITY_CONTEXT_KEY);

        UserDetails principal = (UserDetails) securityContext.getAuthentication().getPrincipal();
        return principal.getUsername();

    }

    @GetMapping
    public List<FormBasicInfo> getUserForms(HttpServletRequest req) {

        String email = extractEmailFromSecurityContext(req);
        return formService.getFormsForUser(email);

    }

    @GetMapping("/submitted")
    public List<FormBasicInfo> getSubmittedForUser(HttpServletRequest req) {

        String email = extractEmailFromSecurityContext(req);
        return formService.getCompletedForUser(email);
    }

    @GetMapping("/assaigned")
    public List<FormBasicInfo> getAssaignedForUser(HttpServletRequest req) {

        String email = extractEmailFromSecurityContext(req);
        return formService.getUncompletedForUser(email);
    }

    @GetMapping("/{id}")
    public FormEntity getForm(@PathVariable String id) {

        return formService.getForm(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> submitForm(@PathVariable String id, @RequestBody FormEntity submitedForm) {

        formService.submitForm(submitedForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}