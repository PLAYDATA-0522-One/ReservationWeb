<%@ page import="Data.Airplane" %>
<%@ page import="Module.*" %>
<%@ page import="javax.servlet.http.HttpServletResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Process Reservation</title>
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

        .reservation-details {
            margin-bottom: 20px;
        }

        .reservation-details label {
            font-weight: bold;
        }

        .seat-details {
            margin-top: 20px;
        }

        .seat-details label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .button-container {
            text-align: center;
        }

        .button-container button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            cursor: pointer;
        }

        .button-container button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Process Reservation</h1>
    <form action="/reservation" method="post">
        <input type="hidden" name="airplaneName" value="${param.airplaneName}">
        <input type="hidden" name="seatName" value="${param.seatName}">
        <!-- Other form inputs or fields if needed -->
        <%--        <input type="submit" name="confirmReservation" value="Confirm Reservation">--%>

        <div class="reservation-details">
            <label>[Reservation Details]</label>
            <p><strong>Airplane Name:</strong> ${param.airplaneName}</p>
            <p><strong>Seat Name:</strong> ${param.seatName}</p>
            <p><strong>User ID:</strong> ${sessionScope.userID}</p>
        </div>

        <div class="seat-details">
            <label>[Seat Details]</label>
            <% Airplane airplane = ModuleManager.getInstance().getReservationModuleByNowMobule().findAirPlane(request.getParameter("airplaneName")); %>
            <p><strong>Airplane Name:</strong> <%= airplane.getAirplaneName() %>
            </p>
            <p><strong>Departure Time:</strong> <%= airplane.getDepartureTime() %>
            </p>
            <p><strong>Start Destination:</strong> <%= airplane.getStartDestination() %>
            </p>
            <p><strong>End Destination:</strong> <%= airplane.getEndDestination() %>
            </p>
        </div>


        <div class="button-container">
            <button name="confirmReservation" onclick="confirmReservation()">Confirm Reservation</button>
        </div>
</div>

<script>
    function confirmReservation() {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/reservation", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    window.location.href = "/confirmation.jsp";  // Redirect to the confirmation page
                } else {
                    alert("Reservation failed. Please try again.");  // Display an error message
                }
            }
        };

        var formData = "airplaneName=" + encodeURIComponent("${param.airplaneName}") + "&seatName=" + encodeURIComponent("${param.seatName}");
        xhr.send(formData);
    }
</script>
</form>
</body>
</html>
