<%@ page import="java.util.List" %>
<%@ page import="Data.Ticket" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ticket List</title>
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

        .ticket-list {
            margin-top: 20px;
        }

        .ticket {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
        }

        .ticket p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Ticket List</h1>
    <div class="ticket-list">
        <% List<Ticket> ticketList = (List<Ticket>) request.getAttribute("ticketList"); %>
        <% if (ticketList != null && !ticketList.isEmpty()) { %>
        <% for (Ticket ticket : ticketList) { %>
        <div class="ticket">
            <p><strong>Ticket Number:</strong> <%= ticket.getTicketNumber() %>
            </p>
            <p><strong>User ID:</strong> <%= ticket.getUserID() %>
            </p>
            <p><strong>Airplane Name:</strong> <%= ticket.getAirplaneName() %>
            </p>
            <p><strong>Departure Time:</strong> <%= ticket.getDepartureTime() %>
            </p>
            <p><strong>Start Destination:</strong> <%= ticket.getStartDestination() %>
            </p>
            <p><strong>End Destination:</strong> <%= ticket.getEndDestination() %>
            </p>
            <p><strong>Seat:</strong> <%= ticket.getSeat() %>
            </p>
        </div>
        <% } %>
        <% } else { %>
        <p>No tickets found.</p>
        <% } %>
    </div>
    <div class="button-container">
        <a href="/main">Go to Main</a>
    </div>
</div>
</body>
</html>
