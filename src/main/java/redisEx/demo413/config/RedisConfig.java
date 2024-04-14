package redisEx.demo413.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@RequiredArgsConstructor
@Configuration
@EnableCaching
@EnableRedisRepositories //Redis를 사용함을 명시하는 어노테이션
/* Redis와의 연결 정보를 설정하고, Redis 데이터를 저장하고 조회하는 데 사용되는
RedisTemplate 객체를 생성하는 역할을 하는 클래스*/
public class RedisConfig {

    // Redis 서버와의 연결 정보를 저장하는 객체이다.
    // redis의 host와 port를 YAML 파일에서 수정할 수 있고
    // redisProperties.getHost(), redisProperties.getPort() 메서드를 통해 가져올 수 있다.
    private final RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        //Lettuce = Redis로의 Connection을 관리
        return new LettuceConnectionFactory(redisProperties.getHost(),redisProperties.getPort());
    }

    @Bean //Serializer 설정을 통해 redis-cli로 직접 데이터를 조회할 수 있도록 설정
    public RedisTemplate<String, Object> redisTemplate(){
        //RedisTemplate = 레디스 데이터를 저장하고 조회하는 기능을 하는 클래스
        //Serializer = 객체 -> JSON 등의 형태로 변환(직렬화)
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //Key-value 전부 String으로 직렬화
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(){
        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory());

        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer())) // Value Serializer 변경(JSON 형태로 직렬화)
                .disableCachingNullValues() //데이터가 null일 경우 caching 하지 않음
                .entryTtl(Duration.ofMinutes(30L)); //유효기간 설정

        builder.cacheDefaults(configuration);

        return builder.build();
    }
}
