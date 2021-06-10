package yzxCrmTest.crm.workbench.web.controller;

import yzxCrmTest.crm.settings.domain.User;
import yzxCrmTest.crm.settings.service.Impl.UserServiceImpl;
import yzxCrmTest.crm.settings.service.UserService;
import yzxCrmTest.crm.utils.DateTimeUtil;
import yzxCrmTest.crm.utils.PrintJson;
import yzxCrmTest.crm.utils.ServiceFactory;
import yzxCrmTest.crm.utils.UUIDUtil;
import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.domain.ActivityRemark;
import yzxCrmTest.crm.workbench.service.ActivityService;
import yzxCrmTest.crm.workbench.service.Impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        }else if ("/workbench/activity/pageList.do".equals(path)){
                //显示列表
                pageList(request,response);
        }else if ("/workbench/activity/delete.do".equals(path)){
                //删除活动
                delete(request,response);
        }else if ("/workbench/activity/getUserListAndActivity.do".equals(path)){
                //给前端返回用户列表和市场活动对象
                getUserListAndActivity(request,response);
        }else if ("/workbench/activity/updateActivity.do".equals(path)){
                //执行修改操作
                update(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
                //获取该对象详细内容
                detail(request,response);
        } else if ("/workbench/activity/getRemarkListByActivityId.do".equals(path)) {
                getRemarkListByActivityId(request,response);
        }else if ("/workbench/activity/deleteRemarkById.do".equals(path)){
                deleteRemarkById(request,response);
        }else if("/workbench/activity/addRemark.do".equals(path)){
                addRemark(request,response);
        }
    }

    private void addRemark(HttpServletRequest request, HttpServletResponse response) {

                String id = UUIDUtil.getUUID();
                String nodeContent = request.getParameter("nodeContent");
                String activityId =request.getParameter("activityId");
                String createTime = DateTimeUtil.getSysTime();
                String createBy = ((User)request.getSession().getAttribute("user")).getName();
                ActivityRemark activityRemark = new ActivityRemark();
                activityRemark.setActivityId(activityId);
                activityRemark.setCreateTime(createTime);
                activityRemark.setNoteContent(nodeContent);
                activityRemark.setId(id);
                activityRemark.setCreateBy(createBy);
                activityRemark.setEditFlag("0");
                ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
                boolean flag = activityService.addRemark(activityRemark);
                PrintJson.printJsonFlag(response,flag);

    }

    private void deleteRemarkById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.deleteRemarkById(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getRemarkListByActivityId(HttpServletRequest request, HttpServletResponse response) {
                String id = request.getParameter("activityId");
                ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
                List<ActivityRemark> activityRemarkList = activityService.getRemarkListByActivityId(id);
                PrintJson.printJsonObj(response,activityRemarkList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity a = activityService.detail(id);
        request.setAttribute("a",a);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }
    private void update(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setCost(cost);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editBy);
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean flag = activityService.update(a);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {
                String id = request.getParameter("id");
                ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
                Map<String,Object> map = activityService.getUserListAndActivity(id);
                PrintJson.printJsonObj(response,map);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
                String ids[] = request.getParameterValues("id");
                ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
                boolean flag = activityService.delete(ids);
                PrintJson.printJsonFlag(response,flag);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        PaginationVO<Activity> vo = as.pageList(map);
        PrintJson.printJsonObj(response, vo);
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
