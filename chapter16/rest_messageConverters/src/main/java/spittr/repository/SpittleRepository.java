package spittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spittr.domain.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/4/9
 */
public interface SpittleRepository extends JpaRepository<Spittle, Long> {

    //找到的spittle会是包括了详细信息的spitter的spittle，
    // jpa会根据数据库中的spittle的spitterId找到对应的spitter，然后显示spittle
    Spittle findById(Long id);

    List<Spittle> findBySpitter(Long spitterId);

}
