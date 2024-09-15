import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Your Supabase details
    private static final String SUPABASE_URL = "https://ckoqxelxxcrzuntjhslk.supabase.co";
    private static final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImNrb3F4ZWx4eGNyenVudGpoc2xrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjY0NDE3NTksImV4cCI6MjA0MjAxNzc1OX0.1x0HFX-cgLbXLOU9cWpVjr6-We-9JjHaorl2Sv2vG9k";
    private static final String SUPABASE_TABLE = "users";

    @Override
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
        if (firstName == null || firstName.isEmpty() ||
            surname == null || surname.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty() ||
            confirmPassword == null || confirmPassword.isEmpty() ||
            adminCode == null || adminCode.length() != 6) {

            response.sendRedirect("error.jsp"); // Redirect to error page if validation fails
            return;
        }

        if (!password.equals(confirmPassword)) {
            response.sendRedirect("error.jsp?error=passwordMismatch");
            return;
        }

        try {
            // Fix: Use URI to handle the deprecated URL(String) constructor
            URI uri = new URI(SUPABASE_URL + "/rest/v1/" + SUPABASE_TABLE);
            URL url = uri.toURL(); // Convert URI to URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("apikey", SUPABASE_API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Create user data to send to Supabase
            Map<String, Object> user = new HashMap<>();
            user.put("first_name", firstName);
            user.put("middle_name", middleName);
            user.put("surname", surname);
            user.put("email", email);
            user.put("password", password);  // In production, passwords should be hashed
            user.put("admin_code", adminCode);

            // Convert to JSON
            Gson gson = new Gson();
            String jsonInputString = gson.toJson(user);

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                response.sendRedirect("success.jsp"); // Redirect to success page
            } else {
                response.sendRedirect("error.jsp?error=dbError"); // Redirect to error page if DB insertion fails
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=invalidURL"); // Redirect on invalid URL error
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=dbError"); // Redirect on general database error
        }
    }
}
