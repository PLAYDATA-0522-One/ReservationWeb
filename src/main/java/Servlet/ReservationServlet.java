package Servlet;

import Data.Airplane;
import Data.Seat;
import Data.Ticket;
import Enums.ModuleType;
import Module.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Module.*;

public class ReservationServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // main 진입 시 모듈 메니저 초기화
        ModuleManager.getInstance().initModuleManager();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModuleManager.getInstance().changeModule(ModuleType.RESERVATION);
        ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule();

        req.setAttribute("airplaneList", module.getAirPlaneList_by_Database());
//        req.getRequestDispatcher("views/airplaneList.jsp").forward(req, resp);
        resp.addCookie(new Cookie("LoginID", "test"));
        req.getRequestDispatcher("views/airplaneList.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// Step 1: Retrieve form data

        String airplaneName = req.getParameter("airplaneName");
        String seatName = req.getParameter("seatName");

        // Step 2: Call the reservationAirPlane_Step2 method
        ModuleManager.getInstance().changeModule(ModuleType.RESERVATION);
        ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule();
        Cookie[] cookies = req.getCookies();
        String userID = "";
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("LoginID"))
                userID = cookies[i].getValue();
        }
        // Pass the form data to the reservationAirPlane_Step2 method
        module.reservationAirPlane_Step2(airplaneName, seatName, userID);
        // Retrieve the seat list
        List<Seat> seatList = module.getSeats_by_Database(airplaneName);
        req.setAttribute("seatList2", seatList);
        // Redirect to the appropriate page
        // Set confirmation message in request attribute
        req.setAttribute("message", "Your reservation has been confirmed!");

        // Redirect to the confirmation page
        req.getRequestDispatcher("views/confirmation.jsp").forward(req, resp);
    }

//        super.doPost(req, resp);
//    }
}
