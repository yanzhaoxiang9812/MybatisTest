package yzxCrmTest.crm.workbench.service;

import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    boolean save(Activity activity);
    PaginationVO<Activity> pageList(Map<String,Object> map);
    boolean delete(String[] ids);
    Map<String, Object> getUserListAndActivity(String id);
    boolean update(Activity a);
    Activity detail(String id);
    List<ActivityRemark> getRemarkListByActivityId(String id);
    boolean deleteRemarkById(String id);
    boolean addRemark(ActivityRemark activityRemark);

    boolean updateRemark(ActivityRemark activityRemark);

    List<Activity> getActivityListByClueId(String cid);

    List<Activity> getActivityListByNameAndNotRelation(Map<String, String> map);
}
