<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Created Successfully</title>
    <style>
        /* General page styles */
        body {
            font-family: 'Montserrat', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f7f7f7;
        }

        .success-container {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        h1 {
            font-size: 28px;
            color: #28a745;
            margin-bottom: 20px;
        }

        p {
            font-size: 18px;
            margin-bottom: 30px;
        }

        .back-to-login-btn {
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .back-to-login-btn:hover {
            background-color: #218838;
        }

        .back-to-login-btn:active {
            background-color: #1e7e34;
        }
    </style>
</head>
<body>

    <div class="success-container">
        <h1>Account Created Successfully!</h1>
        <p>Your account has been successfully created. You can now log in with your credentials.</p>
        <!-- Button to navigate back to the login page -->
        <a href="../FacultyPortal/Faculty.html" class="back-to-login-btn">Back to Login</a>
    </div>

</body>
</html>
