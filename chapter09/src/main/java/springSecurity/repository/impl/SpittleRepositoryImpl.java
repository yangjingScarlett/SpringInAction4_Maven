package springSecurity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import springSecurity.model.Spittle;
import springSecurity.repository.SpittleRepositorty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangjing on 2018/2/12
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepositorty {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spittle> findAll(long max, int count) {
        return jdbcTemplate.query("select * from Spittle where id < ? order by created_at desc limit ?",
                new MySpittleRowMapper(), max, count);
    }

    @Override
    public Spittle findById(Long id) {
        return jdbcTemplate.queryForObject("select * from Spittle where id = ?",
                new MySpittleRowMapper(), id);
    }

    @Override
    public void save(Spittle spittle) {
        jdbcTemplate.update("insert into Spittle(message,created_at,latitude,longitude) values (?, ?, ?, ?)",
                spittle.getMessage(), spittle.getCreatedAt(), spittle.getLatitude(), spittle.getLongitude());
    }

    private class MySpittleRowMapper implements RowMapper<Spittle> {
        @Override
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spittle(rs.getLong("id"), rs.getString("message"),
                    rs.getDate("created_at"), rs.getDouble("latitude"),
                    rs.getDouble("longitude"));
        }
    }

}
