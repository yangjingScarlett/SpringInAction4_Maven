package simpleJavaWeb.initConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 这是一个监听器，在 web 应用启动时被装载，主要用于在 web 应用启动时启动数据库并创建数据库表 ACCOUNT，在 web 应用停止时关闭数据库
 */
public class DataSourceStartListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(DataSourceStartListener.class);

    private Server server;

    /**
     * 在 web 应用启动时启动 h2 数据库
     *
     * @param sce servlet 上下文事件
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("Start H2 database...");
            // 使用org.h2.tools.Server这个类创建一个H2数据库的服务并启动服务，由于没有指定任何参数，那么H2数据库启动时默认占用的端口就是8082
            server = Server.createWebServer().start();
            logger.info("H2 database starts successfully!");
        } catch (SQLException e) {
            logger.error("H2 database initialized failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
        try {
            Connection conn = JdbcUtil.getConnection();
            Statement stmt = conn.createStatement();
            String createTableSql = "CREATE TABLE IF NOT EXISTS Account (accountName varchar(40) PRIMARY KEY, password varchar(16))";
            stmt.execute(createTableSql);
            logger.info("Create ACCOUNT Table successfully!");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            logger.error("Create table failed!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (this.server != null) {
            this.server.stop();
            this.server = null;
            logger.info("Stop H2 database!");
        }
    }
}
