/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.aptech.musicstore.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.aptech.musicstore.entity.News;
import vn.aptech.musicstore.service.NewsService;

/**
 *
 * @author Dung
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/news")
public class NewsApiController {

    @Value("${static.base.url}")
    private String base_url;

    @Autowired
    private NewsService service;

    @GetMapping
    public List<News> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<News> findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public String create(@RequestParam("title") String title,
	    @RequestParam("content") String content,
            @RequestParam("create_at") String date,
            @RequestParam("des") String des,
	    @RequestParam("img_title") String img,
            @RequestParam("file") MultipartFile file) throws IOException {
        News news = new News();
        news.setTitle(title);
	news.setContent(content);
        news.setDescription(des);
	news.setImg_title(img);
        news.setImage(file.getOriginalFilename());
//        String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
//        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
        Files.copy(file.getInputStream(), Paths.get(base_url + "\\news" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        //news.setView(0);
        news.setCreated_at(date);
        service.save(news);
        return "created";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.deleteById(id);
        return "deleted";
    }

    @PutMapping
    public String update(@RequestParam("id") int id,
            @RequestParam("create_at") String date,
            @RequestParam("title") String title,
	    @RequestParam("content") String content,
            @RequestParam("des") String des,
	    @RequestParam("img_title") String img,
            @RequestParam("file") MultipartFile file) throws IOException {
        if (!(file.isEmpty())) {
            News news = new News();
            news.setId(id);
            news.setTitle(title);
	    news.setContent(content);
            news.setDescription(des);
	    news.setImg_title(img);
            news.setImage(file.getOriginalFilename());
            //news.setView(0);
            news.setCreated_at(date);
            service.save(news);
//            String path_directory = "C:\\Users\\namng\\Documents\\NetBeansProjects\\musicstore\\src\\main\\resources\\static\\image";
            //        String path_directory = new ClassPathResource("static/image").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(), Paths.get(base_url + "\\news" + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } else {
            News news2 = service.findById(id).orElseThrow();
            News news = new News();
            news.setId(id);
            news.setTitle(title);
	    news.setContent(content);
            news.setDescription(des);
	    news.setImg_title(img);
            news.setImage(news2.getImage());
            //news.setView(0);
            news.setCreated_at(date);
            service.save(news);
        }
        return "updated";
    }
}
