package spitter.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spitter.domain.Spitter;
import spitter.repository.SpitterRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangjing on 2018/3/13
 */
@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SPITTER = "insert into Spitter (username, password, fullname, email, updateByEmail) values (?, ?, ?, ?, ?)";

    private static final String SELECT_SPITTER = "select id, username, password, fullname, email, updateByEmail from Spitter";

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(id) from Spitter",Integer.TYPE);
    }

    @Override
    public Spitter save(Spitter spitter) {
        Long id = spitter.getId();
        if(id == null){
            long spiterId = insertSpitterAndReturnId(spitter);
            return new Spitter(spiterId,spitter.getUsername(),spitter.getPassword(),spitter.getFullName(),spitter.getEmail(),spitter.isUpdateByEmail());
        }else {
            jdbcTemplate.update("update spitter set username = ?, password = ?, fullname = ?, email = ?, updateByEmail = ? where id = ?",
                    spitter.getUsername(),
                    spitter.getPassword(),
                    spitter.getFullName(),
                    spitter.getEmail(),
                    spitter.isUpdateByEmail(),
                    id);
            return spitter;
        }
    }

    private long insertSpitterAndReturnId(Spitter spitter){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Spitter");
        simpleJdbcInsert.setGeneratedKeyName("id");
        Map<String,Object> args = new HashMap<>();
        args.put("username",spitter.getUsername());
        args.put("password",spitter.getPassword());
        args.put("fullname",spitter.getFullName());
        args.put("email",spitter.getEmail());
        args.put("updateByEmail",spitter.isUpdateByEmail());
        return simpleJdbcInsert.executeAndReturnKey(args).longValue();
    }

    @Override
    public Spitter findById(long id) {
        return jdbcTemplate.queryForObject(SELECT_SPITTER+" where id = ?",new MySpitterRowMapper(),id);
    }

    @Override
    public Spitter findByUsername(String username) {
        return jdbcTemplate.queryForObject("select * from spitter where username = ?",new MySpitterRowMapper(),username);
    }

    @Override
    public List<Spitter> findAll() {
        return jdbcTemplate.query("select * from spitter order by id",new MySpitterRowMapper());
    }

    private static final class MySpitterRowMapper implements RowMapper<Spitter>{

        @Override
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(rs.getLong("id"),rs.getString("username"),
                    rs.getString("password"),rs.getString("fullname"),
                    rs.getString("email"),rs.getBoolean("updateByEmail"));
        }

    }

}
