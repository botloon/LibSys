package lzy_libsys.Controller;

import lzy_libsys.Entity.Book;
import lzy_libsys.Entity.BookCase;
import lzy_libsys.Entity.BookVO;
import lzy_libsys.Service.BookCaseService;
import lzy_libsys.Service.BookService;
import lzy_libsys.Service.Impl.BookCaseServiceImpl;
import lzy_libsys.Service.Impl.BookServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.util.List;

public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();
    private BookCaseService bookCaseService = new BookCaseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        String name = null;
        String author = null;
        String publish = null;
        String pagesStr = null;
        int pages = 0;
        String priceStr = null;
        double price = 0;
        String bookCaseIdStr = null;
        int bookCaseId = 0;
        String idStr = null;
        int id = 0;

        BookCase bookCase = null;
        Book book = null;
            switch(method){
                case "findAll":
                    String pageStr = req.getParameter("page");
                    String limitStr = req.getParameter("limit");
                    int page = Integer.parseInt(pageStr);
                    int limit = Integer.parseInt(limitStr);

                    BookVO bookVO = new BookVO();
                    bookVO = bookService.findAll(page,limit);
                    JSONObject jsonObject = JSONObject.fromObject(bookVO);
                    resp.setContentType("text/json;charset=UTF-8");
                    resp.getWriter().write(jsonObject.toString());
                    break;
                case "getBookCases":
                    List<BookCase> list = bookCaseService.getBookCases();
                    req.setAttribute("list",list);
                    req.getRequestDispatcher("book_add.jsp").forward(req,resp);
                    break;
                case "save":
                    name = req.getParameter("name");
                    author = req.getParameter("author");
                    publish = req.getParameter("publish");
                    pagesStr = req.getParameter("pages");
                    priceStr = req.getParameter("price");
                    bookCaseIdStr = req.getParameter("bookCaseId");
                    pages = Integer.parseInt(pagesStr);
                    price = Double.parseDouble(priceStr);
                    bookCaseId = Integer.parseInt(bookCaseIdStr);

                    bookCase = new BookCase();
                    bookCase.setId(bookCaseId);
                    book = new Book();
                    book.setName(name);
                    book.setAuthor(author);
                    book.setPublish(publish);
                    book.setPages(pages);
                    book.setPrice(price);
                    book.setBookCase(bookCase);
                    bookService.save(book);
                    resp.sendRedirect("book_manage.jsp");
                    break;
                case "deleteById":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    bookService.deleteById(id);
                    resp.sendRedirect("book_manage.jsp");
                    break;
                case "findById":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    Book book1 = bookService.findById(id);
                    List<BookCase> list1 = bookCaseService.getBookCases();
                    req.setAttribute("list1",list1);
                    req.setAttribute("book1",book1);
                    req.getRequestDispatcher("book_update.jsp").forward(req,resp);
                    break;
                case "update":
                    idStr = req.getParameter("id");
                    id = Integer.parseInt(idStr);
                    name = req.getParameter("name");
                    author = req.getParameter("author");
                    publish = req.getParameter("publish");
                    pagesStr = req.getParameter("pages");
                    priceStr = req.getParameter("price");
                    bookCaseIdStr = req.getParameter("bookCaseId");
                    pages = Integer.parseInt(pagesStr);
                    price = Double.parseDouble(priceStr);
                    bookCaseId = Integer.parseInt(bookCaseIdStr);
                    book = new Book();
                    book.setId(id);
                    book.setName(name);
                    book.setAuthor(author);
                    book.setPublish(publish);
                    book.setPages(pages);
                    book.setPrice(price);
                    bookCase = new BookCase();
                    bookCase.setId(bookCaseId);
                    book.setBookCase(bookCase);
                    bookService.update(book);
                    resp.sendRedirect("book_manage.jsp");

                    break;

        }



        //        String pagestr = req.getParameter("page");
//        int page = 0;
//        if(pagestr == null){
//            page=1;
//        }else{
//            page=Integer.parseInt(pagestr);
//        }
//        int pages = bookService.count();
//        List<Book> list = bookService.findAll(page);
//        req.setAttribute("list",list);
//        req.setAttribute("currentpage",page);
//        req.setAttribute("pages",pages);
//        req.getRequestDispatcher("book.jsp").forward(req,resp);
    }
}
