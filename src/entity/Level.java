package entity;

public class Level {

    private Integer level;
    private String name;
    private String text;

    public Level(Integer level, String name, String text) {
        this.level = level;
        this.name = name;
        this.text = text;
    }

    public Level() {
        level = -1;
        name = "";
        text = "";
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
