/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.musicstore.entity.DownloadAllowed;
import vn.aptech.musicstore.repository.DownloadAllowedRepository;
import vn.aptech.musicstore.service.DownloadAllowedService;

/**
 *
 * @author namng
 */
@Service
public class DownloadAllowedServiceImpl implements DownloadAllowedService {

    @Autowired
    private DownloadAllowedRepository repo;

    @Override
    public List<DownloadAllowed> findAll() {
        return repo.findAll();
    }

    @Override
    public DownloadAllowed save(DownloadAllowed obj) {
       return repo.save(obj);
    }

}
