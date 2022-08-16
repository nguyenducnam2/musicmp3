/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity;
//
//import java.util.Calendar;
//import java.util.Date;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
///**
// *
// * @author Administrator
// */
//
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "verification_token")
//public class VerificationToken {
//
//    // Expiration time 10m
//    private static final int EXPIRATION_TIME = 10;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String token;
//    @Temporal(javax.persistence.TemporalType.DATE)
//    private Date expirationTime;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name="FK_USER_VERIFY_TOKEN"))
//    private User user;
//
//    public VerificationToken(String token, User user) {
//        super();
//        this.token = token;
//        this.user = user;
//        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
//    }
//
//    public VerificationToken(String token) {
//        super();
//        this.token = token;
//        this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
//    }
//
//    private Date calculateExpirationDate(int expirationTime) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(new Date().getTime());
//        calendar.add(Calendar.MINUTE, expirationTime);
//        return new Date(calendar.getTime().getTime());
//    }
//
//}
