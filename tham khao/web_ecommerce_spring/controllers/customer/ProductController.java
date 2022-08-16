package com.java.web_ecommerce_spring.controllers.customer;

import ch.qos.logback.core.model.Model;
import com.java.web_ecommerce_spring.domain.*;
import com.java.web_ecommerce_spring.serviceImpls.CommentServiceImpl;
import com.java.web_ecommerce_spring.services.CategoryService;
import com.java.web_ecommerce_spring.services.ProductService;
import com.java.web_ecommerce_spring.utils.Middleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/public/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CommentServiceImpl commentService;

    @Autowired
    CategoryService categoryService;
    Middleware middleware = new Middleware();
    @GetMapping(value = "/detail/{productId}")
    public ModelAndView detail(@PathVariable int productId){
        ModelAndView mv = new ModelAndView("public/detail");
        Product obj = productService.getProductById(productId);
        obj.setViewCount(obj.getViewCount() + 1);
        productService.save(obj);
        List<Comment> listC = commentService.findCommentByProduct(obj);
        float sum = 0;
        for(int i = 0;i < listC.size();i++){
            sum = sum + listC.get(i).getRate();
        }
        float ave = (float)(sum/ listC.size());
        List<Product> productsExam = productService.getProductByCategory(obj.getCategory()).stream().limit(10).collect(Collectors.toList());
        mv.addObject("product",obj);
        mv.addObject("listC",listC);
        mv.addObject("ave",ave);
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("productsExam",productsExam);
        return mv;
    }

    @PostMapping(value = "/comment")
    public ModelAndView comment(HttpServletRequest request){
        User user = middleware.middlewareUser(request);
        String text = request.getParameter("text");
        float rate = Float.parseFloat(request.getParameter("rate"));
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProductById(id);
        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setRate(rate);
        comment.setText(text);
        comment.setUser(user);
        commentService.save(comment);
        String url = "redirect:/public/product/detail/"+id;
        ModelAndView mv = new ModelAndView(url);
        return mv;
    }

    @GetMapping(value = "/{categoryId}")
    public ModelAndView getProductByCategory(@PathVariable int categoryId, @RequestParam("page") Optional<Integer> page){
        ModelAndView mv = new ModelAndView("public/productCategory");
        Pageable pageable = PageRequest.of(page.orElse(0), 12);
        Category category = categoryService.getCategoryById(categoryId);
        Page<Product> recruitments = productService.findProductByCategory(category,pageable);
        List<Product> recruitmentList = productService.getProductByCategory(category);
        int numberPage = recruitmentList.size() / 12;
        if (recruitmentList.size() % 12 != 0){
            numberPage = numberPage +1;
        }
        List<Product> recruitmentSize = recruitmentList.stream().limit(numberPage).collect(Collectors.toList());
        mv.addObject("activeRe",true);
        mv.addObject("list", recruitments);
        mv.addObject("recruitmentList", recruitmentSize);
        mv.addObject("numberPage",page.orElse(0).intValue());
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("category",category);
        return mv;
    }

    @GetMapping(value = "/all")
    public ModelAndView getProductByCategory(@RequestParam("page") Optional<Integer> page){
        ModelAndView mv = new ModelAndView("public/allProduct");
        Pageable pageable = PageRequest.of(page.orElse(0), 12);
        Page<Product> recruitments = productService.findAll(pageable);
        List<Product> recruitmentList = productService.getAll();
        int numberPage = recruitmentList.size() / 12;
        if (recruitmentList.size() % 12 != 0){
            numberPage = numberPage +1;
        }
        List<Product> recruitmentSize = recruitmentList.stream().limit(numberPage).collect(Collectors.toList());
        mv.addObject("activeRe",true);
        mv.addObject("list", recruitments);
        mv.addObject("recruitmentList", recruitmentSize);
        mv.addObject("numberPage",page.orElse(0).intValue());
        mv.addObject("categories",categoryService.getAll());
        return mv;
    }

    @PostMapping(value = "/search")
    public ModelAndView search(@RequestParam("page") Optional<Integer> page, HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String keyWord = request.getParameter("keyWord");
      /*  String url = "redirect:/public/product/search/" +keyWord;*/
        ModelAndView mv = new ModelAndView("public/result-search");
        String keyWordSearch = "%" + keyWord +"%";
        Pageable pageable = PageRequest.of(page.orElse(0), 12);
        Page<Product> recruitments = productService.findProductByNameLike( keyWordSearch,pageable);
        List<Product> recruitmentList = productService.findProductByNameLike(keyWordSearch);
        int numberPage = recruitmentList.size() / 12;
        if (recruitmentList.size() % 12 != 0){
            numberPage = numberPage +1;
        }
        List<Product> recruitmentSize = recruitmentList.stream().limit(numberPage).collect(Collectors.toList());
        mv.addObject("activeRe",true);
        mv.addObject("list", recruitments);
        mv.addObject("recruitmentList", recruitmentSize);
        mv.addObject("numberPage",page.orElse(0).intValue());
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("keySearch",keyWord);
        return mv;
    }

    @GetMapping(value = "/search")
    public ModelAndView getSearch( @RequestParam("page") Optional<Integer> page,HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView("redirect:/public/home");

        return mv;
    }

    @GetMapping(value = "/search/{keyWord}")
    public ModelAndView search(@PathVariable String keyWord, @RequestParam("page") Optional<Integer> page, HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*  String url = "redirect:/public/product/search/" +keyWord;*/
        ModelAndView mv = new ModelAndView("public/result-search");
        String keyWordSearch = "%" + keyWord +"%";
        Pageable pageable = PageRequest.of(page.orElse(0), 12);
        Page<Product> recruitments = productService.findProductByNameLike( keyWordSearch,pageable);
        List<Product> recruitmentList = productService.findProductByNameLike(keyWordSearch);
        int numberPage = recruitmentList.size() / 12;
        if (recruitmentList.size() % 12 != 0){
            numberPage = numberPage +1;
        }
        List<Product> recruitmentSize = recruitmentList.stream().limit(numberPage).collect(Collectors.toList());
        mv.addObject("activeRe",true);
        mv.addObject("list", recruitments);
        mv.addObject("recruitmentList", recruitmentSize);
        mv.addObject("numberPage",page.orElse(0).intValue());
        mv.addObject("categories",categoryService.getAll());
        mv.addObject("keySearch",keyWord);
        return mv;
    }
}
