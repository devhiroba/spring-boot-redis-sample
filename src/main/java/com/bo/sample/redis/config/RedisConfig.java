package com.bo.sample.redis.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
    private final String HOSTNAME;
    private final int PORT;
    private final int DATABASE;
    private final String PASSWORD;
    private final long TIMEOUT;

    public RedisConfig(
            @Value("${redis.hostname}") String hostname,
            @Value("${redis.port}") int port,
            @Value("${redis.database}") int database,
            @Value("${redis.password}") String password,
            @Value("${redis.timeout}") long timeout
    ) {
        this.HOSTNAME = hostname;
        this.PORT = port;
        this.DATABASE = database;
        this.PASSWORD = password;
        this.TIMEOUT = timeout;
    }

    @Bean
    public RedisClusterClient redisClient() {

        // オプション設定
        RedisURI redisUri = RedisURI.Builder.redis(HOSTNAME)
                .withPort(PORT)
                //if (PASSWORD != null){
                //   .withPassword("authentication")
                //}
                //.withDatabase(2)
                //.withTimeout(Duration.ofMillis(10))
                .build();

        // Client作成
        RedisClusterClient client = RedisClusterClient.create(redisUri);

        return client;

    }
}
