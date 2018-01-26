package spittr.repository;

import spittr.model.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/1/16
 */
public interface SpittleRepository {

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    void save(Spittle spittle);

}
