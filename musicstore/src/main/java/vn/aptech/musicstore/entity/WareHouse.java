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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "warehouse")
public class WareHouse {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name="id")
     private Integer id;
      
        
     @JoinColumn(name = "product_id", referencedColumnName = "id")
     @ManyToOne
     private Product product;
     
     @Column(name="quantity")
     private Integer quantity;
     
     
     
    
}
