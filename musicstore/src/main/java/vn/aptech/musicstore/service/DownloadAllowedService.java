/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service;

import java.util.List;
import vn.aptech.musicstore.entity.DownloadAllowed;

/**
 *
 * @author namng
 */
public interface DownloadAllowedService {
    List<DownloadAllowed> findAll();
    
    DownloadAllowed save(DownloadAllowed obj);
}
