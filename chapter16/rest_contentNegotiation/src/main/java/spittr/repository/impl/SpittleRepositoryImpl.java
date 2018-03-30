package spittr.repository.impl;

import org.springframework.stereotype.Repository;
import spittr.domain.Spittle;
import spittr.repository.SpittleCustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by yangjing on 2018/3/27
 */
@Repository
public class SpittleRepositoryImpl implements SpittleCustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Spittle> findRecentSpittles() {
        return findSpittles(Long.MAX_VALUE, 10);
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return entityManager.createQuery("select s from Spittle s where s.id < ?1 order by s.postTime desc")
                .setParameter(1, max)
                .setMaxResults(count)
                .getResultList();
    }

}
