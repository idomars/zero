package me.zhongmin.zero.kafka.internetal;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String address;

    public User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
