/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.entity;

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
 * @author pc
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "brand")
public class Brand {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private Integer id;
     @Column(name="name")
     private String name;
     
     
    
}
