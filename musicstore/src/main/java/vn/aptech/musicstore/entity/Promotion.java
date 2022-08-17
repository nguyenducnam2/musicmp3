/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Administrator
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "promotion")
public class Promotion {
    // Expiration time 10m

    private static final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double discount;
    private String title;
    private String code;
    private Date expirationTime;
    private int useTimes;
    
       @Column(name = "user_id",insertable = false,updatable = false)
    private Long userId;

    
   // @OneToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account acc;

//    private Date calculateExpirationDate(int expirationTime) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(new Date().getTime());
//        calendar.add(Calendar.MINUTE, expirationTime);
//        return new Date(calendar.getTime().getTime());
//    }
}
