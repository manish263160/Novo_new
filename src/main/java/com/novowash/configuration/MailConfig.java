package com.novowash.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


public class MailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private String port;
    
    @Value("maill.username")
    private String userName;
    
    @Value("${mail.password}")
    private String password;

   // @Bean
    public JavaMailSender javaMailService() {
    	  JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
          javaMailSender.setHost(host);
          //javaMailSender.setPort(port);
          javaMailSender.setUsername(userName);
          javaMailSender.setPassword(password);
          
          javaMailSender.setJavaMailProperties(getMailProperties());
          return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.port", port);
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "false");
        properties.setProperty("mail.smtp.starttls.enable", "false");
        properties.setProperty("mail.debug", "false");
        return properties;
    }
}