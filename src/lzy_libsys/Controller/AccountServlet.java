package lzy_libsys.Controller;

import lzy_libsys.Entity.Admin;
import lzy_libsys.Entity.Reader;
import lzy_libsys.Service.AccountService;
import lzy_libsys.Service.Impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "login":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                String type = req.getParameter("type");
                Object object = accountService.login(username,password,type);
                if(object == null){
                    resp.sendRedirect("login.jsp");
                }else{
                    HttpSession session = req.getSession();
                    switch (type){
                        case "admin":
                            session.setAttribute("admin",(Admin)object);
                            resp.sendRedirect("admin.jsp");
                            break;
                        case "reader":
                            session.setAttribute("reader",(Reader)object);
                            resp.sendRedirect("book_reader.jsp");
                    }
                }
                break;
            case "logout":
                HttpSession session = req.getSession();
                session.invalidate();
                resp.sendRedirect("login.jsp");
                break;
        }
    }
}
