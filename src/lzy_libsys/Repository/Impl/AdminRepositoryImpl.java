package lzy_libsys.Repository.Impl;

import lzy_libsys.Entity.Admin;
import lzy_libsys.Repository.AdminRepository;
import lzy_libsys.utils.JDBCTools;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection connection = null;
        connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin where username=? and password = ? ";
        Admin admin = null;
        QueryRunner queryRunner = new QueryRunner();
        try {
            admin = queryRunner.query(connection,sql,new BeanHandler<>(Admin.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCTools.release(connection,null,null);
        }
        return admin;
    }
}
