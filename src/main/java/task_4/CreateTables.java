package task_4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTables {

    private Connection connect = null;
    private Statement statement = null;

    public void createTables() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_4", "root", "Conyeumelam");

        statement = connect.createStatement();

        String tableCourse = "CREATE TABLE IF NOT EXISTS course (" +
                "id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                "course_title VARCHAR(30) NOT NULL,\n" +
                "student_id INTEGER NOT NULL,\n" +
                "teacher_id INTEGER NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "CONSTRAINT course_student_fk FOREIGN KEY (student_id) REFERENCES student(id),\n" +
                "CONSTRAINT course_teacher_fk FOREIGN KEY (teacher_id) REFERENCES teacher(id));";

        String tableStudent = "CREATE TABLE IF NOT EXISTS student (" +
                "id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                "first_name VARCHAR(30) NOT NULL,\n" +
                "last_name VARCHAR(30) NOT NULL,\n" +
                "group_id INTEGER NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "CONSTRAINT student_group_fk FOREIGN KEY (group_id) REFERENCES student_groups(id));";

        String tableTeacher = "CREATE TABLE IF NOT EXISTS teacher(" +
                "id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                "first_name VARCHAR(30) NOT NULL,\n" +
                "last_name VARCHAR(30) NOT NULL,\n" +
                "PRIMARY KEY (id));";

        String tableGroup = "CREATE TABLE IF NOT EXISTS student_groups(" +
                "id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                "name_group VARCHAR(20) NOT NULL,\n" +
                "PRIMARY KEY(id));";

        String tableMarks = "CREATE TABLE IF NOT EXISTS marks(" +
                "mark INTEGER  NOT NULL ,\n" +
                "student_id INTEGER NOT NULL,\n" +
                "course VARCHAR(40) NOT NULL ,\n" +
                "CONSTRAINT marks_student_fk FOREIGN KEY (student_id) REFERENCES student (id));";

        statement.executeUpdate(tableGroup);
        statement.executeUpdate(tableStudent);
        statement.executeUpdate(tableTeacher);
        statement.executeUpdate(tableCourse);
        statement.executeUpdate(tableMarks);
        if (statement != null || connect != null) {
            statement.close();
            connect.close();
        }
    }
}
