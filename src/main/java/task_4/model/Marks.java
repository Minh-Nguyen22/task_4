package task_4.model;

public class Marks {

    private String course;
    private int mark;
    private Student student;

    public Marks() {
    }

    public Marks(String course, int mark, Student student) {
        this.course = course;
        this.mark = mark;
        this.student = student;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
