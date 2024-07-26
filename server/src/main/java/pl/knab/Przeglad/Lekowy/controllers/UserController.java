package pl.knab.Przeglad.Lekowy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import pl.knab.Przeglad.Lekowy.user.UserInfo;
import pl.knab.Przeglad.Lekowy.user.UserLogic;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
@RequestMapping("/user")
public class UserController{


    @Autowired
    UserLogic userLogic;

    private String extractEmailFromSecurityContext(HttpServletRequest req) {

        HttpSession session = req.getSession();
        SecurityContext securityContext = (SecurityContext) session.getAttribute(SPRING_SECURITY_CONTEXT_KEY);

        UserDetails principal = (UserDetails) securityContext.getAuthentication().getPrincipal();
        return principal.getUsername();

    }

    @GetMapping
    public UserInfo getUserInfo(HttpServletRequest req){
        
        String email = extractEmailFromSecurityContext(req);
        return userLogic.getUserBasicInfoByEmail(email);
    }


}