import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://your-database-host:port/dbname"; // replace with your actual database info
    private static final String DB_USER = "your-db-username";
    private static final String DB_PASSWORD = "your-db-password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String firstName = request.getParameter("first_name");
        String middleName = request.getParameter("middle_name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String adminCode = request.getParameter("adminCode");

        // Validate input
        if (firstName == null || firstName.isEmpty() || surname == null || surname.isEmpty() ||
            email == null || email.isEmpty() || password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() || adminCode == null || adminCode.length() != 6) {
            
            response.sendRedirect("error.jsp"); // Redirect to error page if validation fails
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("error.jsp?error=passwordMismatch");
            return;
        }

        // Database insert logic
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (first_name, middle_name, surname, email, password, admin_code) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, middleName);
            statement.setString(3, surname);
            statement.setString(4, email);
            statement.setString(5, password); // Note: You should hash the password before storing it in production
            statement.setString(6, adminCode);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("success.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=dbError");
        }
    }
}
