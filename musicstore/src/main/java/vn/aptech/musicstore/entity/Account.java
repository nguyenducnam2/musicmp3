/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Thanh Sang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

//    private final Set<GrantedAuthority> authorities = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(nullable = false)
    private String password;
    private String fullname;
    private String role;

    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled = false;
    private Boolean isUpgrade = false;

//    @Column(name = "gender")
    private Boolean gender = false;
    private String address;
    private String image;
    private String imageUrl;
    private String phone;

    
    private String provider;
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

}
