package spittr.repository;

import spittr.model.Spitter;

/**
 * Created by yangjing on 2018/1/16
 */
public interface SpitterRepository {

    Spitter findByUsername(String username);

    void save(Spitter spitter);

}