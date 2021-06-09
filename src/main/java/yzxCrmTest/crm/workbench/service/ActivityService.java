package yzxCrmTest.crm.workbench.service;

import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.domain.Activity;

import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);
    PaginationVO<Activity> pageList(Map<String,Object> map);
    boolean delete(String[] ids);
    Map<String, Object> getUserListAndActivity(String id);
    boolean update(Activity a);
    Activity detail(String id);
}
