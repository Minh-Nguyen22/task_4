package task_4.model;

public class Teacher {

    private int id;
    private String first_name;
    private String last_name;

    public Teacher() {
    }

    public Teacher(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getName() {
        String name = first_name + last_name;
        return name;
    }

    public void setName(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
