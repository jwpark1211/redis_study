package redisEx.demo413.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import redisEx.demo413.domain.Member;

public interface RedisCachingRepository extends JpaRepository<Member,Long>{
}
