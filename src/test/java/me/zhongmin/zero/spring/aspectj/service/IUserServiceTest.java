package me.zhongmin.zero.spring.aspectj.service;

import java.util.Collections;

import me.zhongmin.zero.ZeroApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ZeroApplication.class)// 指定spring-boot的启动类 
public class IUserServiceTest {
	
	
	@Autowired
	private IUserDemoService userServiceImpl;
	
	@Test
	public void testSaveUser() {
		userServiceImpl.saveUser("i am a user",1,Collections.singletonMap("key", "value"));
	}

}
