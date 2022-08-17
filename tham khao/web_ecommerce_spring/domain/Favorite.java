package com.java.web_ecommerce_spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "favorite")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "createdAt")
    private String createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
