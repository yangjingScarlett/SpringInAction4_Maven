package spittr.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.model.Spittle;
import spittr.repository.SpittleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by yangjing on 2018/1/16
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittleList = jdbcTemplate.query("select * from Spittle where id < ? order by created_at desc limit ?",
                new MyRowMapper(), max, count);
        return spittleList.size() > 0 ? spittleList : null;
    }

    @Override
    public Spittle findOne(long id) {
        List<Spittle> spittles = jdbcTemplate.query("select * from Spittle where id = ?", new MyRowMapper(), id);
        return spittles.size() > 0 ? spittles.get(0) : null;
    }

    @Override
    public void save(Spittle spittle) {
        jdbcTemplate.update("insert into Spittle(message,created_at,latitude,longitude) VALUES (?,?,?,?)",
                spittle.getMessage(), spittle.getTime(),
                spittle.getLatitude(), spittle.getLongitude());
    }

    private static class MyRowMapper implements RowMapper<Spittle> {

        @Override
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String message = rs.getString("message");
            Date created_at = rs.getDate("created_at");
            double latitude = rs.getDouble("latitude");
            double longitude = rs.getDouble("longitude");
            return new Spittle(id, message, created_at, latitude, longitude);
        }

    }

}
