package lzy_libsys.Entity;

public class Borrow {
    private int id;
    private Book book;
    private Reader reader;
    private String borrowtime;
    private String returntime;
    private int adminid;
    private int state;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public int getAdminid() {
        return adminid;
    }

    public int getState() {
        return state;
    }
}
