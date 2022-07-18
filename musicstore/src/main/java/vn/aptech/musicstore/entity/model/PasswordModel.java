/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity.model;

import lombok.Data;

/**
 *
 * @author Thanh Sang
 */
@Data
public class PasswordModel {

    private String email;
    private String oldPassword;
    private String newPassword;
    
}
