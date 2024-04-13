package redisEx.demo413.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
//value=redis의 keyspace , timeToLive=만료시간(seconds 단위) default = 만료시간 없음
@RedisHash(value = "people", timeToLive = 30)
public class Person {

    @Id
    private Long id;
    private String name;
    private int age;
    private LocalDateTime createdAt;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }
}
