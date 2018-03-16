package com.cache.repository;

import com.cache.domain.Spittle;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yangjing on 2018/3/15
 */
public interface SpittleRepository extends JpaRepository<Spittle, Long>, SpittleRepositoryCustom {

    @Cacheable("spittleCache")
    Spittle findById(long id);

    @Cacheable("spittleCache")
    List<Spittle> findBySpitterId(long spitterId);

    @CachePut(value = "spittleCache", key = "#result.id")
    Spittle save(Spittle spittle);

    @CacheEvict(value = "spittleCache", condition = "")
    void delete(long id);

}
