package com.hibernate4.repository.impl;

import com.hibernate4.domain.Spitter;
import com.hibernate4.repository.SpitterRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public long count() {
        return findAll().size();
    }

    @Override
    public Spitter save(Spitter spitter) {
        Serializable id = currentSession().save(spitter);
        return new Spitter((Long) id, spitter.getUsername(), spitter.getPassword(),
                spitter.getFullName(), spitter.getEmail(), spitter.isUpdateByEmail());
    }

    @Override
    public Spitter findById(long id) {
        return (Spitter) currentSession().get(Spitter.class, id);
    }

    @Override
    public Spitter findByUsername(String username) {
        return (Spitter) currentSession().createCriteria(Spitter.class)
                .add(Restrictions.eq("username", username))
                .list().get(0);
    }

    @Override
    public List<Spitter> findAll() {
        return currentSession().createCriteria(Spitter.class).list();
    }

}
