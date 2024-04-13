package redisEx.demo413.repository;

import org.springframework.data.repository.CrudRepository;
import redisEx.demo413.domain.Person;

public interface PersonRedisRepository extends CrudRepository<Person,String> {
}
