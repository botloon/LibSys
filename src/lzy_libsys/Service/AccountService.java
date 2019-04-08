package lzy_libsys.Service;

import lzy_libsys.Entity.Reader;
import lzy_libsys.Entity.ReaderVO;

import java.util.List;

public interface AccountService {
    public Object login(String username,String password,String type);

    public ReaderVO findAll(int page,int limit);
    public void deleteById(int id);

    public Reader findByReaderId(int id);
    public void update(Reader reader);

    public void save(Reader reader);
 }
