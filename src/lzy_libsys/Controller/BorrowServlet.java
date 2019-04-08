package lzy_libsys.Controller;

import lzy_libsys.Entity.BorrowData2;
import lzy_libsys.Entity.BorrowPie;
import lzy_libsys.Entity.BorrowVO;
import lzy_libsys.Entity.Reader;
import lzy_libsys.Service.BorrowService;
import lzy_libsys.Service.Impl.BorrowServiceImpl;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BorrowServlet extends HttpServlet {
    private BorrowService borrowService = new BorrowServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        HttpSession session = req.getSession();
        Reader reader = (Reader)session.getAttribute("reader");
        int readerid = 0;
        if(reader != null){
            readerid = reader.getId();
        }

        resp.setContentType("text/json;charset=UTF-8");
        String pageStr = null;
        String limitStr = null;
        int page = 0;
        int limit = 0;
        BorrowVO borrowVO = null;
        switch(method){
            case "save":
                String bookidstr = req.getParameter("bookid");
                int bookid = Integer.parseInt(bookidstr);
                borrowService.save(bookid,readerid);
                resp.sendRedirect("reader_borrowed.jsp");
                break;
            case "findByReaderId":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                limitStr = req.getParameter("limit");
                limit = Integer.parseInt(limitStr);

                borrowVO = borrowService.findByReaderId(readerid,page,limit);
                JSONObject jsonObject = JSONObject.fromObject(borrowVO);
                resp.getWriter().write(jsonObject.toString());
                break;
            case "findAll":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                limitStr = req.getParameter("limit");
                limit = Integer.parseInt(limitStr);

                borrowVO = borrowService.findAll(page,limit);
                JSONObject jsonObject1  = JSONObject.fromObject(borrowVO);
                resp.getWriter().write(jsonObject1.toString());
                break;
            case "findNoBack":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                limitStr = req.getParameter("limit");
                limit = Integer.parseInt(limitStr);

                borrowVO = borrowService.findNoBack(page,limit);
                JSONObject jsonObject2 = JSONObject.fromObject(borrowVO);
                resp.getWriter().write(jsonObject2.toString());
                break;
            case "getBarData":
                BorrowData2 borrowData2 = borrowService.getData();
                JSONObject jsonObject3 = JSONObject.fromObject(borrowData2);
                resp.getWriter().write(jsonObject3.toString());
                break;
            case "getPieData":
                List<BorrowPie> list = borrowService.getBorrowPie();
                JSONArray jsonArray = JSONArray.fromObject(list);
                resp.getWriter().write(jsonArray.toString());
                break;

        }
    }

}

