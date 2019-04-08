package lzy_libsys.Controller;

import lzy_libsys.Entity.Admin;
import lzy_libsys.Service.BookService;
import lzy_libsys.Service.BorrowService;
import lzy_libsys.Service.Impl.BookServiceImpl;
import lzy_libsys.Service.Impl.BorrowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        String idStr = req.getParameter("id");
        int id = 0;
        if(idStr != null){
            id = Integer.parseInt(idStr);
        }

        switch (method){
            case "agreeBorrow":
                borrowService.update(id,1,admin.getId());
                resp.sendRedirect("borrow_manage.jsp");
                break;
            case "disagreeBorrow":
                borrowService.update(id,2,admin.getId());
                resp.sendRedirect("borrow_manage.jsp");
                break;
            case "agreeBack":
                borrowService.update(id,3,admin.getId());
                resp.sendRedirect("borrow_back.jsp");
                break;
        }



    }
}
