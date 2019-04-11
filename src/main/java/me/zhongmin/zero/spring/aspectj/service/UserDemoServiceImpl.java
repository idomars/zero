package me.zhongmin.zero.spring.aspectj.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDemoServiceImpl implements IUserDemoService {
	
	@Override
	public String saveUser(String user,Object o,Map<String,Object> map) {
		
		log.info("[保存用户] 正在保存...,user = {}",user);
		try {
			TimeUnit.NANOSECONDS.sleep(100);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return user+"return";
	}
	
}
