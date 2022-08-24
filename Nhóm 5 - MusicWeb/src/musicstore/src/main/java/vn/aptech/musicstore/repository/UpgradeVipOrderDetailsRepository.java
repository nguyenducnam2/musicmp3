/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.aptech.musicstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.aptech.musicstore.entity.SongOrderDetail;
import vn.aptech.musicstore.entity.UpgradeVipOrderDetails;

/**
 *
 * @author Administrator
 */

public interface UpgradeVipOrderDetailsRepository extends JpaRepository<UpgradeVipOrderDetails, Integer>{
    
}
