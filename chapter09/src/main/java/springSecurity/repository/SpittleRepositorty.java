package springSecurity.repository;

import springSecurity.model.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/2/12
 */
public interface SpittleRepositorty {

    List<Spittle> findAll(long max, int count);

    Spittle findById(Long id);

    void save(Spittle spittle);

}
