/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity.model;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Administrator
 */

public class ResponseModel {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

  
    public ResponseModel() {
    }

    public ResponseModel(String success, String message) {
        this.success = success;
        this.message = message;
      
    }
    
    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    
    public void setSuccess(String success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
