package homeworks.hw2.servlet;

import homeworks.hw2.dao.CourseDAO;
import homeworks.hw2.model.Course;
import homeworks.hw2.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    private CourseDAO courseDAO;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            connection = DBUtil.getConnection();
            courseDAO = new CourseDAO(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        DBUtil.closeConnection(connection);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("courseName");
        Course course = new Course();

        try {
            courseDAO.addCourse(course);
            response.sendRedirect(request.getContextPath() + "/courses.jsp");
        } catch (SQLException e) {
        }
    }
}
