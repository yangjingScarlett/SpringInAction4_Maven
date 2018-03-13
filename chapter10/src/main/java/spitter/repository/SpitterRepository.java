package spitter.repository;

import spitter.domain.Spitter;

import java.util.List;

/**
 * Created by yangjing on 2018/3/13
 */
public interface SpitterRepository {

    long count();

    Spitter save(Spitter spitter);

    Spitter findById(long id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();

}
