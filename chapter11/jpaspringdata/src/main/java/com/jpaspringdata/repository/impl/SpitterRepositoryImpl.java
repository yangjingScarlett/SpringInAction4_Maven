package com.jpaspringdata.repository.impl;

import com.jpaspringdata.repository.SpitterSweeper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by yangjing on 2018/3/15
 */
@Repository
public class SpitterRepositoryImpl implements SpitterSweeper {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int eliteSweep() {
        String updateSql = "UPDATE Spitter spitter SET spitter.status = 'Elite'" +
                "WHERE spitter.status = 'Newbie' AND " +
                "spitter.id IN (SELECT s from Spitter s WHERE (SELECT COUNT(spittles) FROM s.spittles spittles) >= 10)";
        return entityManager.createQuery(updateSql).executeUpdate();
    }

}
