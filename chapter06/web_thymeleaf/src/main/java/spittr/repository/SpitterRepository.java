package spittr.repository;

import spittr.model.Spitter;

import java.util.List;

/**
 * Created by yangjing on 2018/1/10
 */
public interface SpitterRepository {

    List<Spitter> findSpitters();

    Spitter findOne(long id);

    Spitter findByUsername(String username);

    void save(Spitter spitter);

}
