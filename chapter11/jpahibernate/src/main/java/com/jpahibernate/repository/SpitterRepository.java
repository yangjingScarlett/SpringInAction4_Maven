package com.jpahibernate.repository;

import com.jpahibernate.domain.Spitter;

import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
public interface SpitterRepository {

    long count();

    Spitter findById(long id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();

    Spitter save(Spitter spitter);

}
