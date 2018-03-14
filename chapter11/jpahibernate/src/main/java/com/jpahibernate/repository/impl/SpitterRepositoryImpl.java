package com.jpahibernate.repository.impl;

import com.jpahibernate.domain.Spitter;
import com.jpahibernate.repository.SpitterRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public Spitter findById(long id) {
        return entityManager.find(Spitter.class, id);
    }

    @Override
    public Spitter findByUsername(String username) {
        return (Spitter) entityManager.createQuery("select s from Spitter s where s.username = ?1")
                .setParameter(1, username)
                .getSingleResult();
    }

    @Override
    public List<Spitter> findAll() {
        return (List<Spitter>) entityManager.createQuery("select s from Spitter s").getResultList();
    }

    @Override
    public Spitter save(Spitter spitter) {
        entityManager.persist(spitter);
        return spitter;
    }

}
