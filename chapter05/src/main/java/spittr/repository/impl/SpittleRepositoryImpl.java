package spittr.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spittr.model.Spittle;
import spittr.repository.SpittleRepository;

import java.util.List;

/**
 * Created by yangjing on 2018/1/9
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spittle> findRecentSpittles() {
        return null;
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return jdbcTemplate.query(
                "select id, message, created_at, latitude, longitude" +
                        " from Spittle" +
                        " where id < ?" +
                        " order by created_at desc limit 20",
                new SpittleRowMapper(), max);
    }

    @Override
    public Spittle findOne(long id) {
        return null;
    }

    @Override
    public void save(Spittle spittle) {

    }
}
