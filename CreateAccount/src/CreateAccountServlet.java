import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/createAccount")
public class CreateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        super.init();

        // Initialize Firebase SDK (Make sure you have your service account JSON key)
        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(getServletContext().getResourceAsStream("/WEB-INF/serviceAccountKey.json")))
                    .setDatabaseUrl("https://<your-project-id>.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            throw new ServletException("Unable to initialize Firebase", e);
        }
    }

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
            // Create Firebase user
            UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(firstName + " " + surname);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(createRequest);

            // Save user details to Firestore
            Firestore db = FirestoreClient.getFirestore();
            Map<String, Object> user = new HashMap<>();
            user.put("first_name", firstName);
            user.put("middle_name", middleName);
            user.put("surname", surname);
            user.put("email", email);
            user.put("admin_code", adminCode);

            db.collection("users").document(userRecord.getUid()).set(user);

            response.sendRedirect("success.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?error=dbError");
        }
    }
}
