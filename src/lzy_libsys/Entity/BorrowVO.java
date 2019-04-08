package lzy_libsys.Entity;

import java.util.List;

public class BorrowVO {
    private int code;
    private String msg;
    private int count;
    private List<Borrow> data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public List<Borrow> getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(List<Borrow> data) {
        this.data = data;
    }
}
