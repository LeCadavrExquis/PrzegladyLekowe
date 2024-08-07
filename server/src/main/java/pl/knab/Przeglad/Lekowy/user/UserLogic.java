package pl.knab.Przeglad.Lekowy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserLogic implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found with this email" + username);
        }

        GrantedAuthority authoritiy = new SimpleGrantedAuthority("ROLE_"+user.getRole().name());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authoritiy);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid username and password");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");

        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public Boolean signUp(UserEntity user) {
        String email = user.getEmail();
        String password = user.getPassword();

        UserEntity isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(password));

        user.setRole(UserRole.USER);
        userRepository.save(user);

        return true;
    }
    
    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public UserInfo getUserBasicInfoByEmail(String email){
        UserInfo result = userRepository.findBasicInfoByEmail(email);
        return result;
    }
}
