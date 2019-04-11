package me.zhongmin.zero.springboot.websocket;

import lombok.Data;

import java.util.Date;

@Data
public class WebSocketMessage {
     public String message;
    public String reamrk;
    public  String date;

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "message='" + message + '\'' +
                ", reamrk='" + reamrk + '\'' +
                '}';
    }
}
