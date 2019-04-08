package lzy_libsys.Entity;

public class Reader {
    private int id;
    private String username;
    private String password;
    private String name;
    private String cardid;
    private String tel;
    private String gender;

    public Reader() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getCardid() {
        return cardid;
    }

    public String getTel() {
        return tel;
    }

    public String getGender() {
        return gender;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", cardid='" + cardid + '\'' +
                ", tel='" + tel + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
