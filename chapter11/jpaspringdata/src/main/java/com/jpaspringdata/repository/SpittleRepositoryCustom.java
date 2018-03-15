package com.jpaspringdata.repository;

import com.jpaspringdata.domain.Spittle;

import java.util.List;

/**
 * Created by yangjing on 2018/3/15
 */
public interface SpittleRepositoryCustom {

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

}
