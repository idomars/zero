package me.zhongmin.zero.springboot.controller;

import me.zhongmin.zero.kafka.internetal.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BaseController {


	
	@RequestMapping("/hello")
	public User hello(){

		return new User(1,"i am Groot","Galaxy");
	}

	@RequestMapping("/login")
	public User login(){
		System.out.println(1);

		return new User(1,"i am Groot","Galaxy login");
	}
}
