import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainPortal/src/Main")
public class Main extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Main() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");

        if ("faculty".equals(role)) {
            // Correct the redirection path
            response.sendRedirect("../FacultyPortal/src/Faculty.html");
        } else if ("parent".equals(role)) {
            // Logic for parent role or redirection
            response.sendRedirect("Parent.html"); // Example: Parent redirection
        } else {
            // Default or error handling
            response.sendRedirect("Error.html");
        }
    }
}
