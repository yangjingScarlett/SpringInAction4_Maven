package com.jpaspringdata.repository.impl;

import com.jpaspringdata.domain.Spittle;
import com.jpaspringdata.repository.SpittleRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by yangjing on 2018/3/15
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        return entityManager.createQuery("select s from Spittle s order by s.postTime desc")
                .setMaxResults(count)
                .getResultList();
    }

}
