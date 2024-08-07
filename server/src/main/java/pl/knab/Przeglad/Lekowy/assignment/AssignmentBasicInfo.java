package pl.knab.Przeglad.Lekowy.assignment;

public interface AssignmentBasicInfo {
    String getId();
    String getName();
    String getDoctorEmail();
    String getUserEmail();
    AssignmentStatus getStatus();
}
