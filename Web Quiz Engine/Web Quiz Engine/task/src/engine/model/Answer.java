package engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


public class Answer {


    private boolean success;
    private String feedback;

    public Answer (boolean success , String feedback){
        this.success = success;
        this.feedback = feedback;
    }

    public Answer () {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
