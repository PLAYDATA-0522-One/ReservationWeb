package Servlet;

import Module.*;
import Enums.ModuleType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ForgotServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/forgot.jsp").forward(req, resp);
        //        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
        String username = req.getParameter("username");
        String newPassword = req.getParameter("newPassword");

        LoginModule loginModule = getLoginModule();
        boolean loggedIn = loginModule.changePW(username, newPassword);
        if (loggedIn) {
            req.setAttribute("username", username);
            req.setAttribute("password", newPassword);
            resp.sendRedirect("views/success.jsp");
        } else {
            resp.sendRedirect("views/fail.jsp");
        }
    }
        //        super.doPost(req, resp);


    private LoginModule getLoginModule(){
        LoginModule module = null;
        module = ModuleManager.getInstance().getLoginModuleByNowMobule();

        if (module == null){
            ModuleManager.getInstance().changeModule(ModuleType.LOGIN);
            module = ModuleManager.getInstance().getLoginModuleByNowMobule();
        }

        return module;
    }
}
