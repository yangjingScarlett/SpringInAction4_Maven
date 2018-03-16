package com.cache.repository;

import com.cache.domain.Spittle;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by yangjing on 2018/3/15
 */
public interface SpittleRepositoryCustom {

    @Cacheable("spittleCache")
    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

}
