/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name = "download_allowed")
public class DownloadAllowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "song_id", insertable = false, updatable = false)
    private Integer songId;
    @Column(name = "account_id", insertable = false, updatable = false)
    private Long accountId;
    @Column(name = "date")
    private Date date = calculateExpirationDate(420);

    @JoinColumn(name = "song_id", referencedColumnName = "id")
    @ManyToOne
    private Song song;

    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @ManyToOne
    private Account account;

    private Date calculateExpirationDate(int expirationTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expirationTime);
        return new Date(calendar.getTime().getTime());
    }
}
