import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forgot-password")
public class ForgotPassword extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve email, phone number, and OTP from request
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String otp = request.getParameter("otp");

        // Input validation
        String errorMessage = validateInputs(email, phone, otp);

        if (errorMessage != null) {
            // If there is an error, send back the error message
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/ForgotPassword.html").forward(request, response);
        } else {
            // If inputs are valid, proceed with the process (e.g., send OTP, reset password)
            boolean isVerified = verifyOtp(otp);

            if (isVerified) {
                // Reset password logic here
                response.sendRedirect("reset-password-success.html");
            } else {
                request.setAttribute("errorMessage", "Invalid Code");
                request.getRequestDispatcher("/ForgotPassword.html").forward(request, response);
            }
        }
    }

    // Function to validate input fields
    private String validateInputs(String email, String phone, String otp) {
        // Validate Email
        if (email != null && email.isEmpty()) {
            return "You must input your Email";
        }

        // Validate Phone Number
        if (phone != null && !phone.matches("^09\\d{9}$")) {
            return "You must input your Mobile Number";
        }

        // Validate OTP
        if (otp == null || otp.isEmpty() || !otp.matches("^\\d{6}$")) {
            return "Invalid Code";
        }

        return null;
    }

    // Function to verify the OTP (Mock Implementation)
    private boolean verifyOtp(String otp) {
        // Replace this with your actual OTP verification logic
        String validOtp = "123456"; // Example OTP for testing
        return otp.equals(validOtp);
    }
}
