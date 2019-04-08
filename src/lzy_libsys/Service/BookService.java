package lzy_libsys.Service;

import lzy_libsys.Entity.Book;
import lzy_libsys.Entity.BookVO;

import java.util.List;

public interface BookService {
    public BookVO findAll(int page, int limit);
    public void save(Book book);

    public void deleteById(int id);
    public Book findById(int id);

    public void update(Book book);
}
