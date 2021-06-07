package yzxCrmTest.crm.workbench.web.controller;

import yzxCrmTest.crm.settings.domain.User;
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
                save(request,response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动添加操作");
        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间：当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        Activity a = new Activity();
        a.setId(id);
        a.setCost(cost);
        a.setStartDate(startDate);
        a.setOwner(owner);
        a.setName(name);
        a.setEndDate(endDate);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = as.save(a);
        PrintJson.printJsonFlag(response, flag);

    }

    private void getUserList(HttpServletRequest request,HttpServletResponse response) {
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = userService.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
