package me.zhongmin.zero.springboot.service;

import me.zhongmin.zero.springboot.entity.User;

public interface IUserService {

    public void save(User user);

    public void delete(User user);


    public Iterable<User> findAll();

    public User findById(int id);
}
