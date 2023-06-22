package Servlet;
import Enums.ModuleType;
import Module.ModuleManager;
import Module.EditModule;
import Module.ReservationModule;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {

        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModuleManager.getInstance().changeModule(ModuleType.EDIT);
//        EditModule module = ModuleManager.getInstance().getEditModuleByNowMobule();
//        module.getAirPlaneList_by_Database();
        //resp.addCookie(new Cookie("LoginID", "test"));

        req.getRequestDispatcher("/views/edit.jsp").forward(req, resp);

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = "";
//        Cookie[] c = req.getCookies();
//        String userID = "";
//        for (int i = 0; i < c.length; i++) {
//            if (c[i].getName().equals("LoginID"))
//                userID = c[i].getValue();
//        }
        //ReservationModule module = ModuleManager.getInstance().getReservationModuleByNowMobule();

        //uper.doPost(req, resp);
    }


}
