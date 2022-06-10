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
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "media")
    private String media;
    @Column(name = "lyric")
    private String lyric;
    @Column(name = "price")
    private Integer price;
    @Column(name = "[view]")
    private Integer view;

    @Column(name = "album_id",insertable = false,updatable = false)
    private Integer albumId;
    @Column(name = "artist_id",insertable = false,updatable = false)
    private Integer artistId;
    @Column(name = "genre_id",insertable = false,updatable = false)
    private Integer genreId;

    @JoinColumn(name = "album_id", referencedColumnName = "id")
    @ManyToOne
    private Album album;
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    @ManyToOne
    private Artist artist;
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    @ManyToOne
    private Genre genre;
 

}
