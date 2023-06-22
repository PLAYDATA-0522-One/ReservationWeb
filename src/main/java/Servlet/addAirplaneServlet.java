package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import Enums.ModuleType;
import Module.ModuleManager;
import Module.EditModule;
public class addAirplaneServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EditModule module = getEditModule();
        req.setAttribute("airplaneList", module.getAirPlaneList_by_Database());

        req.getRequestDispatcher("/views/addAirplane.jsp").forward(req, resp);

        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditModule module = getEditModule();

        String airplaneName = req.getParameter("airplaneName");
        String departureTime = req.getParameter("departureTime");
        String departureAirport = req.getParameter("departureAirport");
        String arrivalAirport = req.getParameter("arrivalAirport");

        if (module.insertAirplane(airplaneName, departureTime, departureAirport, arrivalAirport)){
            //성공 알림
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>alert('추가성공');  location.href='/addAirplane'; </script>");
            writer.close();
        }
        else {
            resp.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>alert('추가실패');  location.href='/addAirplane'; </script>");
            writer.close();
        }
    }

    private EditModule getEditModule(){
        EditModule module = null;
        module = ModuleManager.getInstance().getEditModuleByNowMobule();
        if (module == null){
            ModuleManager.getInstance().changeModule(ModuleType.EDIT);
            module = ModuleManager.getInstance().getEditModuleByNowMobule();
        }

        return module;
    }
}
