package simpleJavaWeb.initConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这个类用于创建数据库连接
 */
public class JdbcUtil {

    private static Logger logger = LoggerFactory.getLogger(JdbcUtil.class);
    private static JdbcConnectionPool cp = null;
    private static final String dbFile = "h2dbConfig.properties";

    static {
        try {
            InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream(dbFile);
            Properties prop = new Properties();
            if (in == null) {
                throw new RuntimeException("Cannot load db config file: " + dbFile);
            }
            prop.load(in);

            String defaultUrl = "jdbc:h2:tcp:~/test";
            String url =
                prop.getProperty("JDBC_URL") == null ? defaultUrl : prop.getProperty("JDBC_URL");
            String defaultUser = "sa";
            String user = prop.getProperty("USER") == null ? defaultUser : prop.getProperty("USER");
            String defaultPwd = "";
            String pwd =
                prop.getProperty("PASSWORD") == null ? defaultPwd : prop.getProperty("PASSWORD");

            logger.info("DB Info: url: " + url + ", user: " + user + ", password: " + pwd);
            // 创建数据库连接池
            cp = JdbcConnectionPool.create(url, user, pwd);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return cp.getConnection();
    }
}
