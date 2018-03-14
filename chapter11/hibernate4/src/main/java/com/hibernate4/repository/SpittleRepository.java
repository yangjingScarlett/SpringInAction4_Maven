package com.hibernate4.repository;

import com.hibernate4.domain.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
public interface SpittleRepository {

    long count();

    Spittle save(Spittle spittle);

    Spittle findById(long id);

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

    List<Spittle> findBySpitterId(long spitterId);

    void delete(long id);

}
