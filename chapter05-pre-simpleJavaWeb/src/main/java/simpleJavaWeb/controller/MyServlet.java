package simpleJavaWeb.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simpleJavaWeb.dao.AccountDao;
import simpleJavaWeb.model.Account;

@WebServlet("/simpleJavaWeb")
public class MyServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(MyServlet.class);
    private String prefix = "/views";
    private String suffix = ".jsp";
    private AccountDao dao = new AccountDao();

    public MyServlet() throws SQLException {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String path = request.getPathInfo();
            logger.info(path);
            if (path.equals("/register")) {
                register(request, response);
            } else if (path.equals("/login")) {
                login(request, response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) {
        try {
            String accountName = request.getParameter("accountName");
            String password = request.getParameter("password");
            if (accountName == null || accountName.trim().isEmpty()) {
                request.setAttribute("msg", "账户名不能为空！");
                request.getRequestDispatcher(prefix + "/register" + suffix)
                    .forward(request, response);
                return;
            }
            if (dao.findAccount(accountName) != null) {
                request.setAttribute("msg", "该账户已经被注册！");
                request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("msg", "密码不能为空！");
                request.getRequestDispatcher(prefix + "/register" + suffix)
                    .forward(request, response);
                return;
            }
            Account account = new Account(accountName, password);
            dao.addAccount(account);
            request.setAttribute("msg", "恭喜，注册成功！");
            request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        String accountName = request.getParameter("accountName");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");

        try {
            if (accountName == null || accountName.trim().isEmpty()) {
                request.setAttribute("msg", "账户名不能为空！");
                request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
                return;
            }
            if (password == null || password.trim().isEmpty()) {
                request.setAttribute("msg", "密码不能为空！");
                request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
                return;
            }
            if (password1 == null || password1.trim().isEmpty()) {
                request.setAttribute("msg", "请再次确认密码！");
                request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
                return;
            }
            if (!password1.trim().equals(password.trim())) {
                request.setAttribute("msg", "两次输入的密码不一致！");
                request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
                return;
            }

            Account account = dao.findAccount(accountName);
            if (account == null) {
                request.setAttribute("msg", "该用户不存在，请先去注册！！");
                request.getRequestDispatcher(prefix + "/login" + suffix).forward(request, response);
            } else {
                String rightPwd = account.getPassword();
                if (!rightPwd.equals(password)) {
                    request.setAttribute("msg", "密码错误，请重试");
                    request.getRequestDispatcher(prefix + "/login" + suffix)
                        .forward(request, response);
                } else {
                    request.setAttribute("msg", "恭喜，登录成功！");
                    request.getRequestDispatcher(prefix + "/home" + suffix)
                        .forward(request, response);
                }
            }
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
