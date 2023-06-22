package Servlet;
import Enums.ModuleType;
import Module.*;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/signup.jsp").forward(req, resp);

//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
//        String passwordConfirm = req.getParameter("password_confirm");

        LoginModule signModule = getLoginModule();
        System.out.println("fail");
        boolean signUp = signModule.signup(username, password, name);
        System.out.println("SUCCESS");
        if (signUp) {
            req.setAttribute("username",username);
            req.setAttribute("password", password);
            req.setAttribute("name", name);
            req.getRequestDispatcher("/success.jsp").forward(req, resp);
        } else {
            System.out.println("SUCCESS2");
            req.getRequestDispatcher("/failure.jsp").forward(req, resp);

        }

//        super.doPost(req, resp);
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


