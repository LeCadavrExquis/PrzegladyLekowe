package pl.knab.Przeglad.Lekowy.user;

import java.util.List;

public interface UserService {
    public List<UserEntity> getAllUser()  ;

    public UserEntity findUserProfileByJwt(String jwt);

    public UserEntity findUserByEmail(String email) ;

    public UserEntity findUserById(String userId) ;

    public List<UserEntity> findAllUsers();
}
