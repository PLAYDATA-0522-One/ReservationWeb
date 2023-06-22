<%@ page import="java.util.ArrayList" %>
<%@ page import="Data.Airplane" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Airplane</title>
    <style>
        table {
            width: 50%;
            margin: 0 auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px;
            text-align: center;
            border: 1px solid black;
        }

        h1, h2 {
            text-align: center;
        }

        .form-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }

        .form-container label {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<h1>Add Airplane</h1>

<div class="form-container">
<form method="post" action="/addAirplane">
    <label for="airplaneName">Airplane Name:</label>
    <input type="text" id="airplaneName" name="airplaneName" required>

    <label for="departureTime">Departure Time:</label>
    <input type="date" id="departureTime" name="departureTime" required>

    <label for="departureAirport">Departure Airport:</label>
    <input type="text" id="departureAirport" name="departureAirport" required>

    <label for="arrivalAirport">Arrival Airport:</label>
    <input type="text" id="arrivalAirport" name="arrivalAirport" required>

    <input type="submit" value="Add Airplane">
</form>
</div>

<h2>Airplane List</h2>

<table>
    <tr>
        <th>Airplane Name</th>
        <th>Departure Time</th>
        <th>Departure Airport</th>
        <th>Arrival Airport</th>
    </tr>
    <%
        ArrayList<Airplane> airplaneList = (ArrayList<Airplane>) request.getAttribute("airplaneList");
        if (airplaneList != null) {
            for (Airplane airplane : airplaneList) {
    %>
    <tr>
        <td><%= airplane.getAirplaneName() %></td>
        <td><%= airplane.getDepartureTime() %></td>
        <td><%= airplane.getStartDestination() %></td>
        <td><%= airplane.getEndDestination() %></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
