/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author namng
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "song_order")
public class SongOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    private Date date = calculateExpirationDate(0);
    @Column(name = "payment")
    private String payment;
    @Column(name = "status")
    private String status;
    @Column(name = "total")
    private Double total;
    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne
    private Account account;

    /*  @JsonIgnore
    @OneToMany(mappedBy = "SongOrder")
    private List<SongOrderDetail> items; */
    @Column(name = "promotion_id", insertable = false, updatable = false)
    private Integer promotionId;
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    @ManyToOne
    private Promotion promotion;

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }

}
