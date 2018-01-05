package a_profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yangjing on 2018/1/3
 */

public class DataSourceConfigTest {

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DataSourceConfig.class)
    @ActiveProfiles("dev")
    public static class DevDataSourceTest {

        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDataSource() {
            assertNotNull(dataSource);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<String> results = jdbcTemplate.query("select id , name from Things", new MyRowMapper());

            System.out.println(results.get(0));
            assertEquals(1, results.size());
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DataSourceConfig.class)
    @ActiveProfiles("prod")
    public static class ProdDataSourceTest {

        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeJndiDataSource() {
            // should be null, because there isn't a datasource configured in JNDI
            assertNull(dataSource);
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(value = "classpath:a_profile/datasource-config.xml")
    @ActiveProfiles("dev")
    public static class DevDataSourceTest_XMLConfig {

        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDataSource() {
            assertNotNull(dataSource);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<String> results = jdbcTemplate.query("select * from Things", new MyRowMapper());
            System.out.println(results.get(0));
        }

    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(value = "classpath:a_profile/datasource-config.xml")
    @ActiveProfiles("prod")
    public static class ProdDataSourceTest_XMLConfig {

        //这里找不到这个DataSource，除非设置成false
        @Autowired(required = false)
        private DataSource dataSource;

        @Test
        public void shouldBeJndiDataSource() {
            assertNull(dataSource);
        }

    }

    static class MyRowMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getLong("id") + ":" + rs.getString("name");
        }

    }

}
