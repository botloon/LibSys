package lzy_libsys.Service;

import lzy_libsys.Entity.*;

import java.util.List;

public interface BorrowService {
    public void save(int bookid,int readerid);
    public BorrowVO findByReaderId(int readerid,int page,int limit);

    public BorrowVO findAll(int page,int limit);

    public void update(int id,int state,int adminid);

    public BorrowVO findNoBack(int page,int limit);

    public BorrowData2 getData();

    public List<BorrowPie> getBorrowPie();
}
