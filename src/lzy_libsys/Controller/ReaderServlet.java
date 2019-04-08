package lzy_libsys.Controller;

import lzy_libsys.Entity.Reader;
import lzy_libsys.Entity.ReaderVO;
import lzy_libsys.Service.AccountService;
import lzy_libsys.Service.Impl.AccountServiceImpl;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReaderServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");


        if(method != null){
            String pageStr = null;
            String limitStr = null;
            int page = 0;
            int limit = 0;
            String idStr = null;
            int id = 0;
            Reader reader = null;

            String username = null;
            String password = null;
            String name = null;
            String tel = null;
            String cardid = null;
            String gender = null;

            resp.setContentType("text/json;charset=UTF-8");

            switch(method){
                case "findAll":
                    pageStr = req.getParameter("page");
                    page = Integer.parseInt(pageStr);
                    limitStr = req.getParameter("limit");
                    limit = Integer.parseInt(limitStr);

                    ReaderVO readerVO = new ReaderVO();
                    readerVO = accountService.findAll(page,limit);
                    JSONObject jsonObject = JSONObject.fromObject(readerVO);
                    resp.getWriter().write(jsonObject.toString());
                    break;
                case "deleteById":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    accountService.deleteById(id);
                    resp.sendRedirect("reader_manage.jsp");
                    break;
                case "findByReaderId":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    reader = accountService.findByReaderId(id);
                    req.setAttribute("reader",reader);
                    req.getRequestDispatcher("reader_update.jsp").forward(req,resp);
                    break;
                case "update":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    username = req.getParameter("username");
                    password = req.getParameter("password");
                    name = req.getParameter("name");
                    tel = req.getParameter("tel");
                    cardid = req.getParameter("cardid");
                    gender = req.getParameter("gender");
                    reader = new Reader();
                    reader.setId(id);
                    reader.setUsername(username);
                    reader.setPassword(password);
                    reader.setName(name);
                    reader.setTel(tel);
                    reader.setCardid(cardid);
                    reader.setGender(gender);
                    accountService.update(reader);
                    resp.sendRedirect("reader_manage.jsp");
                    break;
                case "save":
                    username = req.getParameter("username");
                    password = req.getParameter("password");
                    name = req.getParameter("name");
                    tel = req.getParameter("tel");
                    cardid = req.getParameter("cardid");
                    gender = req.getParameter("gender");
                    reader = new Reader();
                    reader.setUsername(username);
                    reader.setPassword(password);
                    reader.setName(name);
                    reader.setTel(tel);
                    reader.setCardid(cardid);
                    reader.setGender(gender);
                    accountService.save(reader);
                    resp.sendRedirect("reader_manage.jsp");
                    break;
            }


        }
    }
}
