package lzy_libsys.Repository.Impl;

import lzy_libsys.Entity.BookCase;
import lzy_libsys.Repository.BookCaseRepository;
import lzy_libsys.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookCaseRepositoryImpl implements BookCaseRepository {
    QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<BookCase> getBookCases() {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookcase";
        List<BookCase> list = null;
        try {
            list = queryRunner.query(connection,sql,new BeanListHandler<>(BookCase.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection,null,null);
        }

        return list;
    }
}
