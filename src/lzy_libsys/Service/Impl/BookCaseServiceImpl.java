package lzy_libsys.Service.Impl;

import lzy_libsys.Entity.BookCase;
import lzy_libsys.Repository.BookCaseRepository;
import lzy_libsys.Repository.Impl.BookCaseRepositoryImpl;
import lzy_libsys.Service.BookCaseService;

import java.util.List;

public class BookCaseServiceImpl implements BookCaseService {

    private BookCaseRepository bookCaseRepository = new BookCaseRepositoryImpl();

    @Override
    public List<BookCase> getBookCases() {
        return bookCaseRepository.getBookCases();
    }
}
