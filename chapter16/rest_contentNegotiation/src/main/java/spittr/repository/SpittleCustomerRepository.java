package spittr.repository;

import spittr.domain.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/3/27
 */
public interface SpittleCustomerRepository {

    List<Spittle> findRecentSpittles();

    List<Spittle> findSpittles(long max, int count);

}
