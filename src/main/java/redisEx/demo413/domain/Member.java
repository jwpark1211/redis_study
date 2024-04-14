package redisEx.demo413.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue
    private Long id;
    private String email;
    private String password;

    @Builder
    public Member (String email, String password){
        this.email = email;
        this.password = password;
    }

}
