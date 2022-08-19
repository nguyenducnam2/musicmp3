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
    private Date startDate;
    private Date endDate;
    private Date expirationTime;

//    private int duration;
    private int useTimes;

    public Promotion(int id, double discount, String title, String code, Date endDate, int useTimes) {
        this.id = id;
        this.discount = discount;
        this.title = title;
        this.code = code;
        this.startDate = startDate();
        this.endDate = endDate;

//        this.expirationTime = new Date(this.endDate.getTime() - this.startDate.getTime());
        this.useTimes = useTimes;
    }

    private Date startDate() {
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getTime());
    }

//    private Date calculateExpirationDate(int expirationTime) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(new Date().getTime());
//        calendar.add(Calendar.MINUTE, expirationTime);
//        return new Date(calendar.getTime().getTime());
//    }
}
