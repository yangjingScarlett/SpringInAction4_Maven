package spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spittr.domain.Spitter;

/**
 * Created by yangjing on 2018/4/9
 */
public interface SpitterRepository extends JpaRepository<Spitter, Long> {

    Spitter findByUsername(String username);

}
