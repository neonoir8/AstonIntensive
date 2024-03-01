package homeworks.hw2.servlet;

import homeworks.hw2.dao.StudentDAO;
import homeworks.hw2.model.Course;
import homeworks.hw2.model.Student;
import homeworks.hw2.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connection = DBUtil.getConnection();
            studentDAO = new StudentDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        DBUtil.closeConnection(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = request.getParameter("studentName");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Course course = new Course();
        course.setCourseId(courseId);
        Student student = new Student();

        try {
            studentDAO.addStudent(student);
            response.sendRedirect(request.getContextPath() + "/students.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
