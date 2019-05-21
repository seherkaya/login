package com.gpch.login.model;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;

public class ApiResponse  {


    private Boolean successful;
    private int status;
    private String message;
    private Object Objectdata;


    public ApiResponse() {}

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Objectdata;
    }

    public void setData(Object data) {
        this.Objectdata = data;
    }


}
