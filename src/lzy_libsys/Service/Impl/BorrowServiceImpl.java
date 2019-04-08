package lzy_libsys.Service.Impl;

import lzy_libsys.Entity.*;
import lzy_libsys.Repository.BookRepository;
import lzy_libsys.Repository.BorrowRepository;
import lzy_libsys.Repository.Impl.BorrowRepositoryImpl;
import lzy_libsys.Service.BorrowService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BorrowServiceImpl implements BorrowService {
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    @Override
    public void save(int bookid,int readerid) {
        //获取借书时间，并转为yyyy-MM-dd格式
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String borrowtime = simpleDateFormat.format(date);
        //计算归还时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR)+14);
        String returntime = simpleDateFormat.format(calendar.getTime());
        //实例化borrow
        Book book = new Book();
        book.setId(bookid);
        Reader reader = new Reader();
        reader.setId(readerid);

        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setReader(reader);
        borrow.setBorrowtime(borrowtime);
        borrow.setReturntime(returntime);
        borrowRepository.save(borrow);
    }

    @Override
    public BorrowVO findByReaderId(int readerid,int page,int limit) {
        int index = (page-1)*limit;
        List<Borrow> list = borrowRepository.findByReaderId(readerid,index,limit);

        BorrowVO borrowVO = new BorrowVO();
        borrowVO.setCode(0);
        borrowVO.setMsg("");
        borrowVO.setCount(borrowRepository.countByReaderId(readerid));
        borrowVO.setData(list);
        return borrowVO;
    }

    @Override
    public BorrowVO findAll(int page, int limit) {
        int index = (page-1)*limit;
        List<Borrow> list = borrowRepository.findAll(index,limit);
        BorrowVO borrowVO = new BorrowVO();
        borrowVO.setCode(0);
        borrowVO.setCount(borrowRepository.count());
        borrowVO.setData(list);
        return borrowVO;
    }

    @Override
    public void update(int id, int state, int adminid) {
        borrowRepository.update(id, state, adminid);
    }

    @Override
    public BorrowVO findNoBack(int page, int limit) {
        int index = (page-1)*limit;
        BorrowVO borrowVO = new BorrowVO();
        borrowVO.setCode(0);
        borrowVO.setMsg("");
        borrowVO.setCount(borrowRepository.countNoBack());
        borrowVO.setData(borrowRepository.findNoBack(index,limit));
        return borrowVO;
    }

    @Override
    public BorrowData2 getData() {
        List<BorrowData> list = borrowRepository.getData();
        BorrowData2 borrowData2 = new BorrowData2();
        List<String> names = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for(BorrowData borrowData : list){
            names.add(borrowData.getName());
            counts.add(borrowData.getCount());
        }
        borrowData2.setNames(names);
        borrowData2.setCounts(counts);
        return borrowData2;
    }

    @Override
    public List<BorrowPie> getBorrowPie() {
        return borrowRepository.getPieData();
    }
}
