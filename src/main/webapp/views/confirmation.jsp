<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
    <style>
        /* CSS styling for the page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            border: 1px solid #e0e0e0;
            padding: 20px;
        }

        h1 {
            text-align: center;
        }

        .confirmation-message {
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
            font-size: 18px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Reservation Confirmation</h1>
    <div class="confirmation-message">
        <p>Your reservation has been confirmed!</p>
        <p>Thank you for choosing our service.</p>
    </div>
    <div>
        <%-- Add a link to the main page --%>
        <a href="/main">Go to Main Page</a>
    </div>
</div>
</body>
</html>
