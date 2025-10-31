package com.yordanos_bekele.Polling_Voting.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.db")
@Setter
@Getter
public class DBConfig {
    private static String host;
    private static String username;
    private static String password;
    private static String dbname;
}
