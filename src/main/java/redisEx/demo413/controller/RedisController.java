package redisEx.demo413.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import redisEx.demo413.DTO;
import redisEx.demo413.MemberDTO;
import redisEx.demo413.PersonDTO;
import redisEx.demo413.domain.Member;
import redisEx.demo413.service.RedisCachingService;
import redisEx.demo413.service.RedisService;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;
    private final RedisCachingService cachingService;

    @PostMapping("/redisTest/create")
    public ResponseEntity<?> addRedisKey(
            @RequestBody DTO req
    ){
        redisService.setValues(req.getKey(),req.getVal());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/redisTest/hashTest")
    public ResponseEntity<?> addHashKey(
            @RequestBody PersonDTO req
    ){
        redisService.savePerson(req.getName(),req.getAge());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/test/cache/1")
    public ResponseEntity<?> addMember(
            @RequestBody MemberDTO dto
    ){
        cachingService.saveMember(new Member(dto.getEmail(),dto.getPassword()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/test/cache/{member-id}")
    public String getMemberEmail(
            @PathVariable("member-id") Long id
    ){
        String email = cachingService.getMemberEmail(id);
        return email;
    }
}
