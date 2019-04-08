package lzy_libsys.Repository.Impl;

import lzy_libsys.Entity.*;
import lzy_libsys.Repository.BorrowRepository;
import lzy_libsys.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowRepositoryImpl implements BorrowRepository {
    QueryRunner queryRunner = new QueryRunner();

    @Override
    public void save(Borrow borrow) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into borrow(bookid,readerid,borrowtime,returntime,state) values(?,?,?,?,?)";
        try {
            queryRunner.update(connection, sql, borrow.getBook().getId(), borrow.getReader().getId(), borrow.getBorrowtime(), borrow.getReturntime(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, null, null);
        }
    }

    @Override
    public List<Borrow> findByReaderId(int readerid, int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id,br.borrowtime,br.returntime,br.state,b.id,b.name,r.id,r.name from borrow br,book b,reader r where br.readerid = r.id and br.bookid = b.id and br.readerid =? limit ?,?";
        List<Borrow> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readerid);
            preparedStatement.setInt(2, index);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int borrowid = resultSet.getInt(1);
                String borrowTime = resultSet.getString(2);
                String returnTime = resultSet.getString(3);
                int state = resultSet.getInt(4);
                int bookid = resultSet.getInt(5);
                String bookName = resultSet.getString(6);
                int rid = resultSet.getInt(7);
                String readerName = resultSet.getString(8);
                Book book = new Book();
                book.setId(bookid);
                book.setName(bookName);
                Reader reader = new Reader();
                reader.setId(rid);
                reader.setName(readerName);
                Borrow borrow = new Borrow();
                borrow.setId(borrowid);
                borrow.setBorrowtime(borrowTime);
                borrow.setState(state);
                borrow.setReturntime(returnTime);
                borrow.setBook(book);
                borrow.setReader(reader);
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, null, null);
        }
        return list;
    }

    public int countByReaderId(int readerid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(id) from borrow where readerid=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, readerid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }

        return count;
    }

    @Override
    public List<Borrow> findAll(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id,br.borrowtime,br.returntime,br.state,b.id,b.name,r.id,r.name from borrow br,book b,reader r where br.readerid = r.id and br.bookid = b.id and br.state = 0 limit ?,?";
        List<Borrow> list = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int borrowid = resultSet.getInt(1);
                String borrowTime = resultSet.getString(2);
                String returnTime = resultSet.getString(3);
                int state = resultSet.getInt(4);
                int bookid = resultSet.getInt(5);
                String bookName = resultSet.getString(6);
                int rid = resultSet.getInt(7);
                String readerName = resultSet.getString(8);
                Book book = new Book();
                book.setId(bookid);
                book.setName(bookName);
                Reader reader = new Reader();
                reader.setId(rid);
                reader.setName(readerName);
                Borrow borrow = new Borrow();
                borrow.setId(borrowid);
                borrow.setBorrowtime(borrowTime);
                borrow.setState(state);
                borrow.setReturntime(returnTime);
                borrow.setBook(book);
                borrow.setReader(reader);
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public int count() {
        int count = 0;
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow where state = 0";
        try {
            count = ((Long) queryRunner.query(connection, sql, new ScalarHandler<>())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, null, null);
        }
        return count;
    }

    @Override
    public void update(int id, int state, int adminid) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update borrow set state=?,adminid=? where id = ?";
        try {
            queryRunner.update(connection,sql,state,adminid,id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }

    }

    @Override
    public List<Borrow> findNoBack(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select br.id,br.borrowtime,br.returntime,br.state,b.id,b.name,r.id,r.name from borrow br,book b,reader r where b.id=br.bookid and r.id=br.readerid and state=1 limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrow> list = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);
            preparedStatement.setInt(2,limit);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String borrowtime = resultSet.getString(2);
                String returntime = resultSet.getString(3);
                int state = resultSet.getInt(4);
                int bookid = resultSet.getInt(5);
                String bookname = resultSet.getString(6);
                int readerid = resultSet.getInt(7);
                String readername = resultSet.getString(8);
                Book book = new Book();
                book.setId(bookid);
                book.setName(bookname);
                Reader reader = new Reader();
                reader.setId(readerid);
                reader.setName(readername);
                Borrow borrow = new Borrow();
                borrow.setId(id);
                borrow.setBook(book);
                borrow.setReader(reader);
                borrow.setBorrowtime(borrowtime);
                borrow.setReturntime(returntime);
                borrow.setState(state);
                list.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }

        return list;
    }

    @Override
    public int countNoBack() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from borrow where state = 1";
        int count = 0;
        try {
            count = ((Long)queryRunner.query(connection,sql,new ScalarHandler<>())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }

        return count;
    }

    @Override
    public List<BorrowData> getData() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select b.name,count(br.bookid) count from borrow br,book b where br.bookid = b.id group by br.bookid";
        List<BorrowData> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(BorrowData.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public List<BorrowPie> getPieData() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select b.name,count(br.bookid) value from borrow br,book b where br.bookid = b.id group by br.bookid";
        List<BorrowPie> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(BorrowPie.class));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }


    //    public static void main(String[] args) {
//        BorrowRepository borrowRepository = new BorrowRepositoryImpl();
//        System.out.println(borrowRepository.findAll(1,10));
//    }
}