package lzy_libsys.Entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private String publish;
    private int pages;
    private double price;
    private int bookcaseid;

    private BookCase bookCase;

    public int getBookcaseid() {
        return bookcaseid;
    }

    public void setBookcaseid(int bookcaseid) {
        this.bookcaseid = bookcaseid;
    }

    public BookCase getBookCase() {
        return bookCase;
    }

    public void setBookCase(BookCase bookCase) {
        this.bookCase = bookCase;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish() {
        return publish;
    }

    public int getPages() {
        return pages;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
