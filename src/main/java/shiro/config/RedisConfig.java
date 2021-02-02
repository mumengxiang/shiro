package shiro.config;

import org.crazycake.shiro.RedisManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @Author m748124843
 * @Date 2021-01-27 23:04
 * @Version 1.0
 * 概况：Redis的配置
 */
@Configuration
@ConfigurationProperties(prefix = "redis")
public  class RedisConfig {

    /**
     * 配置redis的信息
     */
    private String host;
    private int port;
    private Duration timeout;

    /**
     * 配置redis信息
     * 用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setTimeout((int) timeout.toMillis());
        return redisManager;
    }
}
