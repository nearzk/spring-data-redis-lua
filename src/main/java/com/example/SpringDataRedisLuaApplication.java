package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringDataRedisLuaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataRedisLuaApplication.class, args);
    }

    @Autowired
    private  JavaMailSender mailSender;

    @Bean
    public String sendMail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ecloud@inspur.com");
        message.setTo("ecloud@inspur.com", "zhangkun02@inspur.com");
        message.setSubject("[EABot] Socket连接断开");
        String text = "Bot to chat is disconnected, time: " + LocalDateTime.now().toString();
        message.setText(text);

        mailSender.send(message);
        return "testMail";
    }

}
