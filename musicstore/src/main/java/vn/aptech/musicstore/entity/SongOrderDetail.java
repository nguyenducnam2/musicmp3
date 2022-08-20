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
 * @author namng
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "song_order_detail")
public class SongOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "song_order_id", insertable = false, updatable = false)
    private int songOrderId;
    @Column(name = "song_id", insertable = false, updatable = false)
    private int songId;
    @Column(name = "promotion_id", insertable = false, updatable = false)
    private int promotionId;
    @JoinColumn(name = "song_order_id", referencedColumnName = "id")
    @ManyToOne
    private SongOrder songOrder;
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    @ManyToOne
    private Song song;
    @JoinColumn(name = "promotion_id", referencedColumnName = "id")
    @ManyToOne
    private Promotion promotion;
}
