package entity;

public class NameEducation {

    private int id;
    private String name;

    public NameEducation(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public NameEducation() {
        id = -1;
        name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
