package yzxCrmTest.crm.web.listener;

import yzxCrmTest.crm.settings.domain.DicValue;
import yzxCrmTest.crm.settings.service.DicService;
import yzxCrmTest.crm.settings.service.Impl.DicServiceImpl;
import yzxCrmTest.crm.utils.ServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("start.............");
        DicService dicService = (DicService) ServiceFactory.getService(new DicServiceImpl());
        //System.out.println("全局域对象已创建");
        ServletContext application = sce.getServletContext();
        //取出数据字典 数据保存到全局作用域对象
        Map<String, List<DicValue>> map = dicService.getDV();
        Set<String> set = map.keySet();
        for (String key:set){
            application.setAttribute(key,map.get(key));
        }
        System.out.println("end..................");
    }
}
