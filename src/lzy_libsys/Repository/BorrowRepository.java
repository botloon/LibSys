package lzy_libsys.Repository;

import lzy_libsys.Entity.Borrow;
import lzy_libsys.Entity.BorrowData;
import lzy_libsys.Entity.BorrowPie;

import java.util.List;

public interface BorrowRepository {
    public void save(Borrow borrow);
    public List<Borrow> findByReaderId(int readerid,int index,int limit);
    public int countByReaderId(int readerid);

    public List<Borrow> findAll(int index,int limit);
    public int count();

    public void update(int id,int state,int adminid);

    public List<Borrow> findNoBack(int index,int limit);
    public int countNoBack();

    public List<BorrowData> getData();
    public List<BorrowPie> getPieData();
}
