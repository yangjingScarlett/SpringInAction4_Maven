package com.jpahibernate.repository.impl;

import com.jpahibernate.domain.Spittle;
import com.jpahibernate.repository.SpittleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
@Repository
@EnableTransactionManagement
public class SpittleRepositoryImpl implements SpittleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public Spittle findById(long id) {
        return entityManager.find(Spittle.class, id);
    }

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        //createQuery方法的SQL对应的是实体类而不是数据库表，并且区分大小写
        return entityManager.createQuery("select s from Spittle s order by s.postTime desc")
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public List<Spittle> findBySpitterId(long spitterId) {
        return entityManager.createQuery("select s from Spittle s, Spitter sp where s.spitter=sp and sp.id = ?1 order by s.postTime desc")
                .setParameter(1, spitterId)
                .getResultList();
    }

    @Override
    public List<Spittle> findAll() {
        return entityManager.createQuery("select s from Spittle s order by s.postTime desc")
                .getResultList();
    }

    @Override
    public Spittle save(Spittle spittle) {
        entityManager.persist(spittle);
        return spittle;
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

}
