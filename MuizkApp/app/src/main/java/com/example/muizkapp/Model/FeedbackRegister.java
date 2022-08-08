package com.example.muizkapp.Model;

import com.google.gson.annotations.SerializedName;

public class FeedbackRegister {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    /*public String getMessage() {
        return message;
    }*/
}
