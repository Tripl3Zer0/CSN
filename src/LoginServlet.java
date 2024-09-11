import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Main")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Serve the Main.html file when accessed via GET request
        request.getRequestDispatcher("/Main.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the role from the form submission
        String role = request.getParameter("role");

        // Redirect based on the role selected
        if ("faculty".equals(role)) {
            // Redirect to Login.html for faculty
            response.sendRedirect("/Main Portal/Login.html");
        } else if ("parent".equals(role)) {
            // Redirect to parent-specific page (e.g., parent dashboard)
            response.sendRedirect("/parent-dashboard");
        } else {
            // If the role is invalid, redirect to an error page
            response.sendRedirect("/error");
        }
    }
}
