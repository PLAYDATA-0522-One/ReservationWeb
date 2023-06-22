<%@ page import="Data.Airplane" %>
<%@ page import="java.util.List" %>
<%@ page import="Module.*" %>
<%@ page import="Data.Seat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Seat Reservation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
        }

        form {
            width: 400px;
            margin: 30px auto;
            padding: 20px;
            border: 1px solid #ddd;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        .message {
            text-align: center;
            margin-top: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Seat Reservation</h1>
<form action="processReservation.jsp" method="post">
    <div class="seat-details">
        <%-- Retrieve the seat list using the ReservationModule --%>
        <% ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule(); %>
        <% List<Seat> seatList2 = module.getSeats_by_Database(request.getParameter("airplaneName")); %>

        <%--        <label>예매 불가 좌석:</label>--%>
        <%--        &lt;%&ndash; Loop through the seat list and display the seat details &ndash;%&gt;--%>
        <%--        <% for (Seat seat : seatList2) { %>--%>
        <%--        <p><strong>Seat:</strong> <%= seat.getSeatName() %>--%>
        <%--        </p>--%>
        <%--        <% } %>--%>
    </div>
    <input type="hidden" name="airplaneName" value="<%= request.getParameter("airplaneName") %>">
    <label for="seatName">Select a seat:</label>
    <select id="seatName" name="seatName" style="width: 300px; height: 30px; font-size: 16px;">
        <% Airplane airplane = ModuleManager.getInstance().getReservationModuleByNowMobule().findAirPlane(request.getParameter("airplaneName")); %>
        <% List<String> seatList = airplane.getSeatList(); %>
        <% List<Seat> reservedSeats = module.getSeats_by_Database(request.getParameter("airplaneName")); %>
        <% for (String seat : seatList) { %>
        <% boolean isReserved = false; %>
        <% for (Seat reservedSeat : reservedSeats) { %>
        <% if (reservedSeat.getSeatName().equals(seat)) { %>
        <% isReserved = true; %>
        <%--        <% break; %>--%>
        <% } %>
        <% } %>
        <% if (!isReserved) { %>
        <option value="<%= seat %>"><%= seat %>
        </option>
        <% } %>
        <% } %>
    </select>
    <br>
    <br>
    <input type="submit" value="Reserve Seat">
</form>
<%--<div class="message">--%>
<%--    &lt;%&ndash; Display any reservation messages or errors here &ndash;%&gt;--%>
<%--    <%= request.getAttribute("message") %>--%>
<%--</div>--%>
</body>
</html>
