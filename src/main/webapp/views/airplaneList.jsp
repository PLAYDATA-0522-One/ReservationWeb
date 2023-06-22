<%@ page import="Data.Airplane" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Airplane List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
        }

        table {
            width: 80%;
            margin: 30px auto;
            border-collapse: collapse;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f8f8f8;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f5f5f5;
            cursor: pointer;
        }
    </style>
    <script>
        function goToSeatReservation(airplaneName) {
            window.location.href = "views/seatReservation.jsp?airplaneName=" + encodeURIComponent(airplaneName);
        }
    </script>
</head>
<body>
<h1>Airplane List</h1>
<table>
    <tr>
        <th>Airplane Name</th>
        <th>Departure Time</th>
        <th>Start Destination</th>
        <th>End Destination</th>
    </tr>
    <%-- Loop through the airplane list and display the information --%>
    <% List<Airplane> airplaneList = (List<Airplane>) request.getAttribute("airplaneList"); %>
    <% for (Airplane airplane : airplaneList) { %>
    <tr onclick="goToSeatReservation('<%= airplane.getAirplaneName() %>')">
        <td><%= airplane.getAirplaneName() %>
        </td>
        <td><%= airplane.getDepartureTime() %>
        </td>
        <td><%= airplane.getStartDestination() %>
        </td>
        <td><%= airplane.getEndDestination() %>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
