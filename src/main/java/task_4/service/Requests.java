package task_4.service;

import task_4.model.Student;
import task_4.model.Teacher;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requests {

    public List<Teacher> getTeachersWithMore2000Students(List<String> courses) {
        List<Teacher> teachers = new ArrayList<Teacher>();
        List<Integer> teachersId = new ArrayList<Integer>();
        PreparedStatement statement;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_4", "root", "Conyeumelam");
            for (String course : courses) {
                statement = connection.prepareStatement("SELECT teacher_id FROM course WHERE course_title = ? > 2000");
                statement.setString(1, String.valueOf(course));
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("teacher_id");
                    teachersId.add(id);
                }
            }
            for (int id : teachersId) {
                statement = connection.prepareStatement("SELECT first_name, last_name FROM teacher WHERE id = ?");
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    String first_name = result.getString("first_name");
                    String last_name = result.getString("last_name");
                    Teacher teacher = new Teacher(first_name, last_name);
                    teachers.add(teacher);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public List<Student> getStudentsWhoHaveAnAverageGradeOfMoreThan4(String course) {
        List<Student> students = new ArrayList<Student>();
        List<Integer> studentsId = new ArrayList<Integer>();
        PreparedStatement statement;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_4", "root", "Conyeumelam");
            statement = connection.prepareStatement("SELECT student_id FROM marks WHERE course = ? and mark > 4");
            statement.setString(1, course);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                studentsId.add(id);
            }
            for (int id : studentsId) {
                statement = connection.prepareStatement("SELECT first_name, last_name, group_id FROM student WHERE id = ?");
                statement.setInt(1, id);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    String first_name = result.getString("first_name");
                    String last_name = result.getString("last_name");
                    int groupId = result.getInt("group_id");
                    Student student = new Student(first_name, last_name, groupId);
                    students.add(student);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return students;
    }

    public int numberOfExcellentStudents() {
        PreparedStatement statement;
        ResultSet result;
        int count = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_4", "root", "Conyeumelam");
            statement = connection.prepareStatement("SELECT COUNT(DISTINCT student_id) as count FROM marks WHERE mark > 4;");
            result = statement.executeQuery();
            while (result.next()) {
                count = result.getInt("count");
            }
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
        return count;
    }

    public Map<String, Integer> getNumberOfStudentsInGroups() {
        PreparedStatement statement;
        ResultSet resultSet;
        Map<String, Integer> students = new HashMap<String, Integer>();
        ArrayList<Integer> groupsId = new ArrayList<Integer>();
        int count = 0;
        String nameOfGroup = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_4", "root", "Conyeumelam");
            statement = connection.prepareStatement("SELECT DISTINCT group_id FROM student;");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int groupId = resultSet.getInt("group_id");
                groupsId.add(groupId);
                for (int id : groupsId) {
                    statement = connection.prepareStatement("SELECT name_group FROM student_groups WHERE id = ?");
                    statement.setInt(1, id);
                    ResultSet result = statement.executeQuery();
                    while (result.next()) {
                        nameOfGroup = result.getString("name_group");
                    }
                    statement = connection.prepareStatement("SELECT COUNT(*) as count FROM student WHERE group_id = ?");
                    statement.setInt(1, id);
                    result = statement.executeQuery();
                    while (result.next()) {
                        count = result.getInt("count");
                    }
                    students.put(nameOfGroup, count);
                }
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return students;
    }
}
