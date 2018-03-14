package com.hibernate4.repository.impl;

import com.hibernate4.domain.Spittle;
import com.hibernate4.repository.SpittleRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session curentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public long count() {
        return spittleCriteria().list().size();
    }

    @Override
    public Spittle save(Spittle spittle) {
        Serializable id = curentSession().save(spittle);
        return new Spittle((Long) id, spittle.getSpitter(), spittle.getMessage(), spittle.getPostedTime());
    }

    @Override
    public Spittle findById(long id) {
        return (Spittle) curentSession().get(Spittle.class, id);
    }

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        return spittleCriteria().setMaxResults(count).list();
    }

    @Override
    public List<Spittle> findBySpitterId(long spitterId) {
        return curentSession().createCriteria(Spittle.class)
                .add(Restrictions.eq("spitter.id", spitterId))//一定要是"spitter.id"，而不是spitter
                .list();
    }

    @Override
    public void delete(long id) {
        curentSession().delete(findById(id));
    }

    private Criteria spittleCriteria() {
        return curentSession().createCriteria(Spittle.class)
                .addOrder(Order.desc("postedTime"));
    }

}
