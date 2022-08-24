/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Thanh Sang
 */

@Entity
@Data
@NoArgsConstructor
@Table(name = "viptoken")
public class VipToken {

    // Expiration time 10m
    private static final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expirationTime;
    
    @Column(name = "user_id",insertable = false,updatable = false)
    private Long userId;

    
   // @OneToOne(fetch = FetchType.EAGER)
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account acc;

    public VipToken(Account acc, String token,int duration) {
        super();
        this.token = token;
        this.acc = acc;
        this.expirationTime = calculateExpirationDate(duration);
    }

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}

