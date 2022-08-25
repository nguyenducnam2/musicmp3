/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity;

import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    private int discount;
    private String title;
    private String code;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private int useTimes;

    public Promotion(int id, int discount, String title, String code, Date startDate, Date endDate, int useTimes) {
        this.id = id;
        this.discount = discount;
        this.title = title;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;

//        this.expirationTime = new Date(this.endDate.getTime() - this.startDate.getTime());
        this.useTimes = useTimes;
    }

}
