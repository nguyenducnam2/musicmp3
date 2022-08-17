package com.java.web_ecommerce_spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DiscountCode")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountCode {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "percent")
    private int percent;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

}
