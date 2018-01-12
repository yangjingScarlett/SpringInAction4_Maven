package spittr.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.model.Spitter;
import spittr.repository.SpitterRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yangjing on 2018/1/10
 */
@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Spitter> findSpitters() {
        return jdbcTemplate.query("select * from Spitter order by id desc", new SpitterRowMapper());
    }

    @Override
    public Spitter findOne(long id) {
        return jdbcTemplate.queryForObject("select * from Spitter where id = ?", new SpitterRowMapper(), id);
    }


    @Override
    public Spitter findByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from Spitter where username = ?", new SpitterRowMapper(), username);
    }

    @Override
    public void save(Spitter spitter) {
        jdbcTemplate.update("insert into Spitter values(?, ?, ?, ?, ?, ?) ",
                spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                spitter.getFirstName(), spitter.getLastName(), spitter.getEmail());
    }

    private static class SpitterRowMapper implements RowMapper<Spitter> {

        @Override
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(rs.getLong("id"),
                    rs.getString("username"), rs.getString("password"),
                    rs.getString("first_name"), rs.getString("last_name"),
                    rs.getString("email"));
        }

    }

}
