package simpleJavaWeb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simpleJavaWeb.initConfig.JdbcUtil;
import simpleJavaWeb.model.Account;

public class AccountDao {

    private Logger logger = LoggerFactory.getLogger(AccountDao.class);

    private Connection conn;
    private Statement stmt;

    public AccountDao() throws SQLException {
        conn = JdbcUtil.getConnection();
        stmt = conn.createStatement();
    }

    public void addAccount(Account account) {
        String accountName = account.getAccountName();
        String password = account.getPassword();
        Account registeredAccount = findAccount(accountName);
        try {
            if (registeredAccount == null) {
                String sql =
                    "INSERT INTO ACCOUNT VALUES('" + accountName + "','" + password + "');";
                stmt.executeUpdate(sql);
                logger.info("Insert a new account successfully!");
            } else {
                logger.info("This account has already been registered.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Account findAccount(String accountName) {
        String sql = "SELECT * FROM ACCOUNT WHERE ACCOUNTNAME = '" + accountName + "';";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String password = rs.getString(2);
                logger.info("Find " + accountName + " successfully!");
                return new Account(accountName, password);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Find " + accountName + " failed!");
        }
    }

    public Account findAccount(String accountName, String password) {
        String sql =
            "SELECT * FROM ACCOUNT WHERE ACCOUNTNAME = '" + accountName + "' and PASSWORD = '"
                + password + "';";
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                logger.info("Find " + accountName + " successfully!");
                return new Account(accountName, password);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Find " + accountName + " failed!");
        }
    }

}
