package springSecurity.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import springSecurity.model.Spitter;
import springSecurity.repository.SpitterRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yangjing on 2018/2/12
 */
@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Spitter findByUserName(String username) {
        return jdbcTemplate.queryForObject("select * from Spitter where username = ?",
                new MySpitterRowMapper(), username);
    }

    @Override
    public void save(Spitter spitter) {
        jdbcTemplate.update(
                "insert into Spitter (username, password, first_name, last_name, email)" +
                        " values (?, ?, ?, ?, ?)",
                spitter.getUsername(),
                spitter.getPassword(),
                spitter.getFirstName(),
                spitter.getLastName(),
                spitter.getEmail());
    }

    private class MySpitterRowMapper implements RowMapper<Spitter> {

        @Override
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(rs.getLong("id"), rs.getString("username"),
                    rs.getString("password"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("email"));
        }
    }

}
