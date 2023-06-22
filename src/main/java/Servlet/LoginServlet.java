package Servlet;
import Enums.ModuleType;
import Module.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.Scanner;


public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
//        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);

//        super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginModule loginModule = getLoginModule();
        boolean loggedIn = loginModule.login(username, password);
        if (loggedIn) {
            req.setAttribute("username",username);
            req.setAttribute("password", password);
            req.getRequestDispatcher("success.css").forward(req, resp);
        } else {
            req.getRequestDispatcher("failure.jsp").forward(req, resp);
        }
    }


    @Override
    public void destroy() {
//        super.destroy();
    }

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

