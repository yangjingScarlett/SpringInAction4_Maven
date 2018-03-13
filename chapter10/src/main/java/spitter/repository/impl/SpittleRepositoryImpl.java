package spitter.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spitter.domain.Spitter;
import spitter.domain.Spittle;
import spitter.repository.SpittleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangjing on 2018/3/13
 */
@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    private static final String SELECT_SPITTLE = "select sp.id, s.id as spitterId, s.username, s.password, s.fullname, s.email, s.updateByEmail, sp.message, sp.postedTime from Spittle sp, Spitter s where sp.spitter = s.id";
    private static final String SELECT_SPITTLE_BY_ID = SELECT_SPITTLE + " and sp.id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public long count() {
        return jdbcTemplate.queryForObject("select count(id) from Spittle",Integer.TYPE);
    }

    @Override
    public Spittle findById(long id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_SPITTLE_BY_ID,
                    new MySpittleRowMapper(),id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Spittle> findAll() {
        return jdbcTemplate.query(SELECT_SPITTLE,new MySpittleRowMapper());
    }

    @Override
    public Spittle save(Spittle spittle) {
        long spittleId = insertSpittleAndReturnId(spittle);
        return new Spittle(spittleId,spittle.getSpitter(),spittle.getMessage(),spittle.getPostedTime());
    }

    private long insertSpittleAndReturnId(Spittle spittle){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Spittle");
        simpleJdbcInsert.setGeneratedKeyName("id");
        Map<String,Object> args = new HashMap<>();
        args.put("message",spittle.getMessage());
        args.put("spitter",spittle.getSpitter().getId());
        args.put("postedTime",spittle.getPostedTime());
        return simpleJdbcInsert.executeAndReturnKey(args).longValue();
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from Spittle where id = ? ",id);
    }

    private static final class MySpittleRowMapper implements RowMapper<Spittle> {

        @Override
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String message = rs.getString("message");
            Date postedTime = rs.getTimestamp("postedTime");
            long spitterId = rs.getLong("spitterId");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String fullName = rs.getString("fullname");
            String email = rs.getString("email");
            boolean updateByEmail = rs.getBoolean("updateByEmail");
            Spitter spitter = new Spitter(spitterId, username, password, fullName, email, updateByEmail);
            return new Spittle(id, spitter, message, postedTime);
        }

    }

}
