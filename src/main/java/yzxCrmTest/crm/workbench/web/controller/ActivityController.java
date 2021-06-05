package yzxCrmTest.crm.workbench.web.controller;

import yzxCrmTest.crm.settings.domian.User;
import yzxCrmTest.crm.settings.service.Impl.UserServiceImpl;
import yzxCrmTest.crm.settings.service.UserService;
import yzxCrmTest.crm.utils.DateTimeUtil;
import yzxCrmTest.crm.utils.PrintJson;
import yzxCrmTest.crm.utils.ServiceFactory;
import yzxCrmTest.crm.utils.UUIDUtil;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.service.ActivityService;
import yzxCrmTest.crm.workbench.service.Impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/workbench/activity/getUserList.do".equals(path)){
                //向前端展示userlist
                getUserList(request,response);
        }else if ("/workbench/activity/saveActivity.do".equals(path)){
                //市场活动添加
                saveActivity(request,response);
        }
    }

    private void saveActivity(HttpServletRequest request, HttpServletResponse response) {
                Activity activity = new Activity(
                        UUIDUtil.getUUID(),
                        request.getParameter("owner"),
                        request.getParameter("name"),
                        request.getParameter("startDate"),
                        request.getParameter("endDate"),
                        request.getParameter("cost"),
                        request.getParameter("description"),
                        DateTimeUtil.getSysTime(),
                        ((User)request.getSession().getAttribute("user")).getName(), null, null);
                ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
                boolean flag = activityService.save(activity);
                PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request,HttpServletResponse response) {
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = userService.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
