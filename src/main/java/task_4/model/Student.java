package task_4.model;

public class Student {

    private int id;
    private String first_name;
    private String last_name;
    private int group_id;

    public Student() {
    }

    public Student(String first_name, String last_name, int group_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.group_id = group_id;
    }

    public String getStudent() {
        String student = first_name + last_name + group_id;
        return student;
    }
}
