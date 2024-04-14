package redisEx.demo413.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redisEx.demo413.domain.Member;
import redisEx.demo413.repository.RedisCachingRepository;

@Service
@RequiredArgsConstructor
public class RedisCachingService {

    private final RedisCachingRepository repository;

    /* @Cacheable : 캐시에 데이터가 있으면 메소드 로직을 실행하지 않고 바로 캐시에서 데이터 반환,
       만약 캐시에 데이터가 없다면 메소드 내부 로직 실행 후 캐시에 데이터를 저장하여 반환
       @CachePut : 무조건 메서드 내부에 로직을 실행하고 캐시에 데이터를 저장 후 반환, 보통 갱신 시 이용됨
       @CacheEvict : 키 값에 해당하는 데이터를 삭제함
    * */
    public void saveMember(Member member){
        repository.save(member);
    }

    @Cacheable("member_email")
    public String getMemberEmail(Long memberId){
        String email = repository.findById(memberId).get().getEmail();
        return email;
    }

}
