package lzy_libsys.Service.Impl;

import lzy_libsys.Entity.Book;
import lzy_libsys.Entity.BookVO;
import lzy_libsys.Repository.BookRepository;
import lzy_libsys.Repository.Impl.BookRepositoryImpl;
import lzy_libsys.Service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public BookVO findAll(int page, int limit) {
        int index = (page-1)*limit;
        BookVO bookVO = new BookVO();
        List<Book> list = bookRepository.findAll(index, limit);
        bookVO.setCode(0);
        bookVO.setMsg("");
        bookVO.setCount(bookRepository.count());
        bookVO.setData(list);
        return bookVO;
    }

    @Override
    public void deleteById(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }


}
