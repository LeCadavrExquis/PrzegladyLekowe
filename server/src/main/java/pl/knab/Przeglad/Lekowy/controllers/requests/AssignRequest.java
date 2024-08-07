package pl.knab.Przeglad.Lekowy.controllers.requests;

public class AssignRequest {
    private String name;
    private String userEmail;
    private String templateId;

    public AssignRequest() {
    }

    public AssignRequest(String name, String description, String userEmail, String templateId) {
        this.name = name;
        this.userEmail = userEmail;
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}