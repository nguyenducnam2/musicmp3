/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity.model;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Administrator
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    private int id;
    @NotEmpty
    @Length(min = 6)
    private String username;

    @NotEmpty
    @Length(min = 6)
    private String password;

    private String fullname;
    private String role;
    private Boolean isEdit = false;
    private Boolean enabled = false;
}
