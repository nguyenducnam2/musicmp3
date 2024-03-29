/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String matchingPassword;
    
     private Long id;
    private String username;
//    private String fullname;
    private String role;
//    
  
    private boolean enabled = true;
    private boolean isEdited = true;
    
//    @Column(name = "gender")
//    private boolean gender = false;
    
    private String address;
    private String image;
    private String phone;
}
