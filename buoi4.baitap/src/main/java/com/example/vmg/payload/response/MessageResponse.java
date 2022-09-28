package com.example.vmg.payload.response;

import javax.validation.constraints.NotNull;

public class MessageResponse {

    @NotNull(message = "message cannot null")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageResponse(String message) {
        this.message = message;
    }
}
