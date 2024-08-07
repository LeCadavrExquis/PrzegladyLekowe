package pl.knab.Przeglad.Lekowy.user;

public interface UserInfo {
    
    String getEmail();
    String getFirstName();
    String getLastName();
    UserRole getRole();
}
