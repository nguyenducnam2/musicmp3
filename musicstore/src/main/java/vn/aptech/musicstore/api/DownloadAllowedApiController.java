/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.aptech.musicstore.entity.DownloadAllowed;
import vn.aptech.musicstore.service.DownloadAllowedService;

/**
 *
 * @author namng
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/downloadallowed")
public class DownloadAllowedApiController {
    
    @Autowired
    private DownloadAllowedService service;
    
    @GetMapping
    public List<DownloadAllowed> findAll(){
        return service.findAll();
    }
}
