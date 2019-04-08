package lzy_libsys.Repository.Impl;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;
import lzy_libsys.Entity.Reader;
import lzy_libsys.Repository.ReaderRepository;
import lzy_libsys.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReaderRepositoryImpl implements ReaderRepository {
    QueryRunner queryRunner = new QueryRunner();
    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username = ? and password = ?";
        Reader reader = null;
        try {
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }

        return reader;
    }

    @Override
    public List<Reader> findAll(int index, int limit) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader limit ?,?";
        List<Reader> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(Reader.class),index,limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select count(*) from reader ";
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
    public void deleteById(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "delete from reader where id =?";
        try {
            queryRunner.update(connection,sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public Reader findByReaderId(int id) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where id = ?";
        Reader reader = null;
        try {
            reader = queryRunner.query(connection,sql,new BeanHandler<>(Reader.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
        return reader;
    }

    @Override
    public void update(Reader reader) {
        Connection connection = JDBCTools.getConnection();
        String sql = "update reader set username=?,password=?,name=?,tel=?,cardid=?,gender=? where id=?";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getCardid(),reader.getGender(),reader.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
    }

    @Override
    public void save(Reader reader) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into reader(username,password,name,tel,cardid,gender) values(?,?,?,?,?,?)";
        try {
            queryRunner.update(connection,sql,reader.getUsername(),reader.getPassword(),reader.getName(),reader.getTel(),reader.getCardid(),reader.getGender());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCTools.release(connection,null,null);
        }
    }
}
