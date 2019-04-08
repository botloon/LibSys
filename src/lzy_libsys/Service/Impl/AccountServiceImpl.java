package lzy_libsys.Service.Impl;

import lzy_libsys.Entity.Reader;
import lzy_libsys.Entity.ReaderVO;
import lzy_libsys.Repository.AdminRepository;
import lzy_libsys.Repository.Impl.AdminRepositoryImpl;
import lzy_libsys.Repository.Impl.ReaderRepositoryImpl;
import lzy_libsys.Repository.ReaderRepository;
import lzy_libsys.Service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type){
            case "admin":
                object = adminRepository.login(username,password);
                break;
            case "reader":
                object = readerRepository.login(username,password);
                break;
        }


        return object;
    }

    @Override
    public ReaderVO findAll(int page, int limit) {
        int index = (page-1)*limit;
        ReaderVO readerVO = new ReaderVO();
        readerVO.setCode(0);
        readerVO.setMsg("");
        readerVO.setCount(readerRepository.count());
        readerVO.setData(readerRepository.findAll(index,limit));
        return readerVO;
    }

    @Override
    public void deleteById(int id) {
        readerRepository.deleteById(id);
    }

    @Override
    public Reader findByReaderId(int id) {
        return readerRepository.findByReaderId(id);
    }

    @Override
    public void update(Reader reader) {
        readerRepository.update(reader);
    }

    @Override
    public void save(Reader reader) {
        readerRepository.save(reader);
    }
}
