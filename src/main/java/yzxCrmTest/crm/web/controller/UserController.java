package yzxCrmTest.crm.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("welcome service");
        String path = request.getServletPath();
        if ("/settings/user/xxx.do".equals(path)){
            //
        }else if ("/settings/user/xxx.do".equals(path)){

        }
    }
}
