package spitter.repository;

import spitter.domain.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/3/13
 */
public interface SpittleRepository {

    long count();

    Spittle findById(long id);

    List<Spittle> findAll();

    Spittle save(Spittle spittle);

    void delete(long id);
}
