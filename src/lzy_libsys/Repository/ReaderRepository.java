package lzy_libsys.Repository;


import lzy_libsys.Entity.Borrow;
import lzy_libsys.Entity.Reader;

import java.util.List;

public interface ReaderRepository {
    public Reader login(String username, String password);

    public List<Reader> findAll(int index,int limit);
    public int count();

    public void deleteById(int id);

    public Reader findByReaderId(int id);
    public void update(Reader reader);

    public void save(Reader reader);
}
