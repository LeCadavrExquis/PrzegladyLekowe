package pl.knab.Przeglad.Lekowy.controllers.requests;

public class SubmitRequest {
    Object anwser;

    public SubmitRequest(){}
    
    public SubmitRequest(Object anwser){
        this.anwser = anwser;
    }
    public Object getAnwser(){
        return anwser;
    }
    public void setAnwser(Object anwser){
        this.anwser = anwser;
    }
}
