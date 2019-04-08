package lzy_libsys.Repository;

import lzy_libsys.Entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> findAll(int index,int limit);
    public int count();

    public void save(Book book);
    public void deleteById(int id);
    public Book findById(int id);
    public void update(Book book);

}
