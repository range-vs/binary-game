package entity;

public class BestResult {

    private Integer number;
    private String login;
    private String level;
    private Integer count;
    private String time;

    public BestResult(Integer number, String login, String level, Integer count, String time) {
        this.number = number;
        this.login = login;
        this.level = level;
        this.count = count;
        this.time = time;
    }

    public BestResult() {
        number = 0;
        login = "";
        level = "";
        count = 0;
        time = "";
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
