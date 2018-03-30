package spittr.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import spittr.domain.Spittle;

/**
 * Created by yangjing on 2018/3/26
 */
public interface SpittleRepository extends JpaRepository<Spittle, Long> {

    //其实在repository中最好不要使用缓存，因为无法识别自定义的key值，但是默认的key值很多时候不可用
    @Cacheable(value = "spittleCache")
    Spittle findById(long id);

}
