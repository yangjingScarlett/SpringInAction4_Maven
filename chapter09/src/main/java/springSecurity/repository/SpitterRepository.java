package springSecurity.repository;

import springSecurity.model.Spitter;

/**
 * Created by yangjing on 2018/2/12
 */
public interface SpitterRepository {

    Spitter findByUserName(String username);

    void save(Spitter spitter);

}
