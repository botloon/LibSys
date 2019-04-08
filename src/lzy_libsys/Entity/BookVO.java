package lzy_libsys.Entity;

import java.util.List;

public class BookVO {
    private int code;
    private String msg;
    private int count;
    private List<Book> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public List<Book> getData() {
        return data;
    }
}
