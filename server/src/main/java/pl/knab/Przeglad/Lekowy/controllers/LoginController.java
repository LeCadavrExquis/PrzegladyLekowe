package pl.knab.Przeglad.Lekowy.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.knab.Przeglad.Lekowy.user.UserEntity;
import pl.knab.Przeglad.Lekowy.user.UserLogic;


import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RestController
public class LoginController {

    @Autowired
    private UserLogic userLogic;

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> createUserHandler(HttpServletRequest req, @RequestBody UserEntity user)  {
        String email = user.getEmail();
        String password = user.getPassword();
        Boolean success = userLogic.signUp(user);
        if (!success) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        SecurityContext sc = SecurityContextHolder.getContext();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(email, password);
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/signin")
    public ResponseEntity<HttpStatus> login(HttpServletRequest req, @RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        try {
            Authentication authentication = userLogic.authenticate(email, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);
            HttpSession session = req.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
