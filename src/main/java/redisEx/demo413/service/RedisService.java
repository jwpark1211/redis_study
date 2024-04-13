package redisEx.demo413.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import redisEx.demo413.domain.Person;
import redisEx.demo413.repository.PersonRedisRepository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String,Object> redisTemplate;
    private final PersonRedisRepository personRedisRepository;

    //setValues() = key와 data에 저장한다.
    public void setValues(String key, String data){
        ValueOperations<String,Object> values = redisTemplate.opsForValue();
        values.set(key,data);
    }

    public void setValues(String key, String data, Duration duration){
        ValueOperations<String,Object> values = redisTemplate.opsForValue();
        values.set(key,data,duration);
    }

    @Transactional(readOnly = true)
    public String getValues(String key){
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if(values.get(key)==null) return "false";
        return (String)values.get(key);
    }

    public void deleteValues(String key){
        redisTemplate.delete(key);
    }

    public void expireValues(String key, int timeout){
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }

    //조회하려는 데이터가 없으면 false를 반환한다.
    public boolean checkExistsValue(String value){
        return !value.equals("false");
    }

    public void savePerson(String name, int age){
        personRedisRepository.save(new Person(name,age));
    }

}
