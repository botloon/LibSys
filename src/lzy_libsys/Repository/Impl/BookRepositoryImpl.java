package lzy_libsys.Repository.Impl;


import lzy_libsys.Entity.Book;
import lzy_libsys.Entity.BookCase;
import lzy_libsys.Repository.BookRepository;
import lzy_libsys.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<Book> findAll(int index,int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book b,bookcase bc where b.bookcaseid=bc.id  order by b.id limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int bookid = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String author = resultSet.getString(3);
                String publish = resultSet.getString(4);
                int pages = resultSet.getInt(5);
                double price = resultSet.getDouble(6);
                int bookcaseid = resultSet.getInt(9);
                String bookcasename = resultSet.getString(10);
                BookCase bookCase = new BookCase();

                bookCase.setId(bookcaseid);
                bookCase.setName(bookcasename);

                Book book = new Book();
                book.setId(bookid);
                book.setName(name);
                book.setAuthor(author);
                book.setPublish(publish);
                book.setPages(pages);
                book.setPrice(price);
                book.setBookCase(bookCase);
                list.add(book);
            }
        } catch (SQLException e) {

        }finally {
            JDBCTools.release(connection,null,null);
        }

        return list;
    }

    public int count(){
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(id) from book";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public void save(Book book) {
        Connection connection =JDBCTools.getConnection();
        String sql = "insert into book(name,author,publish,pages,price,bookcaseid,abled) values(?,?,?,?,?,?,1)";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookCase().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public void deleteById(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from book where id = ?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public Book findById(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from book where id = ?";
        Book book = null;
        try {
            book = queryRunner.query(connection,sql,new BeanHandler<>(Book.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public void update(Book book) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update book set name=?,author=?,publish=?,pages=?,price=?,bookcaseid=? where id=?";
        try {
            queryRunner.update(connection,sql,book.getName(),book.getAuthor(),book.getPublish(),book.getPages(),book.getPrice(),book.getBookCase().getId(),book.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
    }

    //    public static void main(String[] args) {
//        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
//        bookRepository.count();
//    }

}
