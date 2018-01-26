package spittr.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.model.Spitter;
import spittr.repository.SpitterRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangjing on 2018/1/16
 */
@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Spitter findByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from Spitter where username = ?",
                new MyRowMapper(), username);
    }

    @Override
    public void save(Spitter spitter) {
        jdbcTemplate.update("insert into Spitter values (?,?,?,?,?,?)",
                spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                spitter.getFirstName(), spitter.getLastName(), spitter.getEmail());
    }

    private static class MyRowMapper implements RowMapper<Spitter> {

        @Override
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String email = rs.getString("email");
            return new Spitter(id, username, password, firstName, lastName, email);
        }

    }

}
