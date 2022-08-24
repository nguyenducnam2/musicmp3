/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderOnline {

    private double price;
    private String currency;
    private int orderId;
    private String method;
    private String intent;
    private String description;

}