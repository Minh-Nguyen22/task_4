package task_4.model;

public class Course {

    private int id;
    private String course_title;
    private Teacher teacher;
    private Student student;

    public Course() {
    }

    public Course(String course_title, Teacher teacher) {
        this.course_title = course_title;
        this.teacher = teacher;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
