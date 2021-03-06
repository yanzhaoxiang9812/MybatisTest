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
import yzxCrmTest.crm.workbench.domain.Clue;
import yzxCrmTest.crm.workbench.service.ActivityService;
import yzxCrmTest.crm.workbench.service.ClueService;
import yzxCrmTest.crm.workbench.service.Impl.ActivityServiceImpl;
import yzxCrmTest.crm.workbench.service.Impl.ClueServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueController extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("j进入线索模块");
        String path = request.getServletPath();
        if ("/workbench/clue/getUserList.do".equals(path)){
            //向前端展示userlist
            getUserList(request,response);
        }else if ("/workbench/clue/save.do".equals(path)){
            save(request,response);
        }else if ("/workbench/clue/pageList.do".equals(path)){
            pageList(request,response);
        }else if ("/workbench/clue/detail.do".equals(path)){
            detail(request,response);
        }else if ("/workbench/clue/getActivityListByClueId.do".equals(path)){
            getActivityListByClueId(request,response);
        }else if ("/workbench/clue/unbund.do".equals(path)){
            unbund(request,response);
        }else if ("/workbench/clue/getActivityListByNameAndNotRelation.do".equals(path)){
            getActivityListByNameAndNotRelation(request,response);
        }else if ("/workbench/clue/bund.do".equals(path)){
            bund(request,response);
        }else if ("/workbench/clue/getActivityListByName.do".equals(path)){
            getActivityListByName(request,response);
        }else if ("/workbench/clue/convert.do".equals(path)){
            convert(request,response);
        }
    }

    private void convert(HttpServletRequest request, HttpServletResponse response) {
        String clueId = request.getParameter("clueId");
        String flag = request.getParameter("flag");
        if(flag!=null){
            //需要创建交易
        }
    }

    private void getActivityListByName(HttpServletRequest request, HttpServletResponse response) {
        String aname = request.getParameter("aname");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = as.getActivityListByName(aname);
        PrintJson.printJsonObj(response,aList);
    }

    private void bund(HttpServletRequest request, HttpServletResponse response) {
            String cid = request.getParameter("cid");
            String aids[] = request.getParameterValues("aid");
            ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
            boolean flag = cs.bund(cid,aids);
            PrintJson.printJsonFlag(response,flag);

    }

    private void getActivityListByNameAndNotRelation(HttpServletRequest request, HttpServletResponse response) {
        String cid = request.getParameter("cid");
        String aname = request.getParameter("aname");
        Map<String,String> map = new HashMap<String, String>();
        map.put("cid",cid);
        map.put("aname",aname);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = as.getActivityListByNameAndNotRelation(map);
        PrintJson.printJsonObj(response,aList);
    }

    private void unbund(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = cs.unbund(id);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getActivityListByClueId(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("clueId");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        List<Activity> aList = as.getActivityListByClueId(id);
        PrintJson.printJsonObj(response,aList);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        Clue c = cs.detail(id);
        request.setAttribute("c",c);
        request.getRequestDispatcher("/workbench//clue/detail.jsp").forward(request,response);
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        String fullname = request.getParameter("fullname");
        String company = request.getParameter("company");
        String  phone = request.getParameter("phone");
        String  source = request.getParameter("source");
        String owner = request.getParameter("owner");
        String  mphone = request.getParameter("mphone");
        String  state = request.getParameter("state");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        int skipCount = (pageNo-1)*pageSize;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fullname",fullname);
        map.put("company",company);
        map.put("phone",phone);
        map.put("source",source);
        map.put("owner",owner);
        map.put("mphone",mphone);
        map.put("state",state);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        PaginationVO<Clue> vo = cs.pageList(map);
        PrintJson.printJsonObj(response, vo);

    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        Clue clue = new Clue(
                UUIDUtil.getUUID(),
                request.getParameter("fullname"),
                request.getParameter("appellation"),
                request.getParameter("owner"),
                request.getParameter("company"),
                request.getParameter("job"),
                request.getParameter("email"),
                request.getParameter("phone"),
                request.getParameter("website"),
                request.getParameter("mphone"),
                request.getParameter("state"),
                request.getParameter("source"),
                ((User)request.getSession().getAttribute("user")).getName(),
                DateTimeUtil.getSysTime(),
               null,
                null,
                request.getParameter("description"),
                request.getParameter("contactSummary"),
                request.getParameter("nextContactTime"),
                request.getParameter("address")
        );
        ClueService cs = (ClueService) ServiceFactory.getService(new ClueServiceImpl());
        boolean flag = cs.save(clue);
        PrintJson.printJsonFlag(response, flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = userService.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
