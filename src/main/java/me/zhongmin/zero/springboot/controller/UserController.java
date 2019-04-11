package me.zhongmin.zero.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import me.zhongmin.zero.springboot.entity.User;
import me.zhongmin.zero.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping(value="/user")
@Slf4j
public class UserController {

    @Resource
    private IUserService userService;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;
/*  @RequestMapping("/save")
    public String save(@RequestParam(required=false) User user){ 
        userService.save(user); 
        return "save ok"; 
    }*/

    @RequestMapping("/delete")
    public String delete(@RequestParam(required=false) User user){
        userService.delete(user);
        return "delete ok";
    }

    @RequestMapping("/{id}")
    public User findAll(@PathVariable Integer id, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("sessionClass = {}",session.getClass());
        log.info("sessionid = {}",session.getId());
        return userService.findById(id);
    }

    @RequestMapping("/list")
    public Iterable<User> findAll(){
        return userService.findAll();
    }

    @RequestMapping("/save")
    public String saveByName(@RequestParam String name){
        User user = new User();
        user.setNickname(name);
        user.setCreatetime(new Date());
        userService.save(user);
        return "save ok";
    }
}  