import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Admin code (6-character code)
        String adminCode = request.getParameter("adminCode");

        // Validate input
        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() ||
            adminCode == null || adminCode.length() != 6) {

            // If validation fails, redirect to error page
            response.sendRedirect("error.jsp");
            return;
        }

        if (!password.equals(confirmPassword)) {
            // Passwords do not match, handle accordingly
            response.sendRedirect("error.jsp?error=passwordMismatch");
            return;
        }

        // If validation succeeds, process form (save to database or another action)
        // For simplicity, just redirecting to success page
        response.sendRedirect("success.jsp");
    }
}
