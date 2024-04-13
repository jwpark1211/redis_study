package redisEx.demo413.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import redisEx.demo413.DTO;
import redisEx.demo413.service.RedisService;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/redisTest/create")
    public ResponseEntity<?> addRedisKey(
            @RequestBody DTO req
    ){
        redisService.setValues(req.getKey(),req.getVal());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}