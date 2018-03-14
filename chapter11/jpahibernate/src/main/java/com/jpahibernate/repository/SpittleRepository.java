package com.jpahibernate.repository;

import com.jpahibernate.domain.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
public interface SpittleRepository {

    long count();

    Spittle findById(long id);

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

    List<Spittle> findBySpitterId(long spitterId);

    List<Spittle> findAll();

    Spittle save(Spittle spittle);

    void delete(long id);

}
