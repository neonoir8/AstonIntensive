package homeworks.hw2.model;

import lombok.Data;

import java.util.List;

@Data
public class Course {

    private int courseId;
    private String courseName;
    private List<Student> students;
}
