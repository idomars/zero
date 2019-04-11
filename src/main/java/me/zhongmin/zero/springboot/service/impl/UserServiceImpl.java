package me.zhongmin.zero.springboot.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.zhongmin.zero.springboot.entity.User;
import me.zhongmin.zero.springboot.dao.UserRepository;
import me.zhongmin.zero.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }


    public void save(User user){
        userRepository.save(user);
    }


    public void delete(User user){
        userRepository.delete(user);
    }


    public Iterable<User> findAll(){
        return userRepository.findAll();
    }
    @Cacheable(cacheNames = "user")
    public User findById(int id){
        log.info("[查询用户] 用户id = {}",id);
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent()?optionalUser.get():null;
    }
}
