package lzy_libsys.Repository;

import lzy_libsys.Entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
