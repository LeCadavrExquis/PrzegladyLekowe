package pl.knab.Przeglad.Lekowy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import pl.knab.Przeglad.Lekowy.assignment.AssignmentBasicInfo;
import pl.knab.Przeglad.Lekowy.assignment.AssignmentEntity;
import pl.knab.Przeglad.Lekowy.assignment.AssignmentService;
import pl.knab.Przeglad.Lekowy.controllers.requests.AssignRequest;
import pl.knab.Przeglad.Lekowy.controllers.requests.SubmitRequest;
import pl.knab.Przeglad.Lekowy.template.TemplateBasicInfo;
import pl.knab.Przeglad.Lekowy.template.TemplateService;
import pl.knab.Przeglad.Lekowy.user.UserInfo;
import pl.knab.Przeglad.Lekowy.user.UserLogic;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    UserLogic userLogic;

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    TemplateService templateService;

    private String extractEmailFromSecurityContext(HttpServletRequest req) {

        HttpSession session = req.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute(SPRING_SECURITY_CONTEXT_KEY);

        UserDetails principal = (UserDetails) securityContext.getAuthentication().getPrincipal();
        return principal.getUsername();

    }

    // USER, DOCTOR
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    @RequestMapping("/user")
    public UserInfo getUserInfo(HttpServletRequest req) {

        String email = extractEmailFromSecurityContext(req);
        return userLogic.getUserBasicInfoByEmail(email);
    }

    // USER, DOCTOR
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    @GetMapping("/assignments")
    public List<AssignmentBasicInfo> getAssignmentsForUser(HttpServletRequest req) {
        String email = extractEmailFromSecurityContext(req);
        return assignmentService.getAssignmentsFor(email);
    }

    //DOCTOR
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PostMapping("/assignment")
    public ResponseEntity<HttpStatus> assign(HttpServletRequest req, @RequestBody AssignRequest assignRequest) {

        String name = assignRequest.getName();
        String doctorEmail = extractEmailFromSecurityContext(req);
        String userEmail = assignRequest.getUserEmail();
        Object form = templateService.getById(assignRequest.getTemplateId()).getForm();
        
        assignmentService.assign(name, doctorEmail, userEmail, form);
        return new ResponseEntity<>(HttpStatus.OK);
        
    }

    //DOCTOR
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping("/templates")
    public List<TemplateBasicInfo> getTemplates(HttpServletRequest req) {
        return templateService.getAllTemplatesBasicInfo();

    }

    // USER, DOCTOR
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DOCTOR')")
    @GetMapping("assignment/{id}")
    public AssignmentEntity getAssignment(HttpServletRequest req, @PathVariable String id) {
        String email = extractEmailFromSecurityContext(req);
        try {
            AssignmentEntity assignment = assignmentService.getAssignment(id, email);
            return assignment;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // USER
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("assignment/{id}")
    public ResponseEntity<HttpStatus> submit(HttpServletRequest req, @PathVariable String id, @RequestBody SubmitRequest submitRequest) {
        String email = extractEmailFromSecurityContext(req);
        Object anwser = submitRequest.getAnwser();
        try {
            assignmentService.submit(id, anwser, email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}