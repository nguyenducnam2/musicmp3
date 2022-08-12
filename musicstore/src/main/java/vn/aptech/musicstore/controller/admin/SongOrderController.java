/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.controller.admin;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.aptech.musicstore.entity.SongOrderDetail;
import vn.aptech.musicstore.service.SongOrderDetailService;
import vn.aptech.musicstore.service.SongOrderService;

/**
 *
 * @author namng
 */
@Controller
@RequestMapping("/admin/songorder")
public class SongOrderController {

    @Autowired
    private SongOrderService service;
    
    @Autowired
    private SongOrderDetailService serviceDt;

    @GetMapping
    public String findAll(Model model, @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        model.addAttribute("list", service.getPage(pageNumber, size));
        return "admin/songorder/index";
    }
    
    @GetMapping("/{id}")
    public String findById(Model model,@PathVariable("id")int id){
        List<SongOrderDetail> list=new ArrayList<>();
        for(SongOrderDetail item:serviceDt.findAll()){
            if(item.getSongOrderId()==id){
                list.add(item);
            }
        }
        model.addAttribute("list",list);
        model.addAttribute("songorder", service.findById(id).get());
        return "admin/songorder/detail";
    }
}
