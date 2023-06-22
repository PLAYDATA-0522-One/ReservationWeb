package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import Enums.ModuleType;
import Module.*;


public class TicketServlet extends HttpServlet {
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
        resp.addCookie(new Cookie("LoginID", "test"));
        Cookie[] cookies = req.getCookies();
        String userID = "";
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("LoginID"))
                userID = cookies[i].getValue();
        }
        req.setAttribute("ticketList", module.getMyTickets_by_Database(userID));
        req.getRequestDispatcher("views/ticketList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
