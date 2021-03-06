package yzxCrmTest.crm.workbench.dao;

import yzxCrmTest.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int save(Activity activity);
    List<Activity> getActivityListByCondition(Map<String, Object> map);
    int getTotalByCondition(Map<String, Object> map);
    int deleteById(String[] ids);
    Activity getActivityById(String id);
    int update(Activity a);
    Activity detail(String id);

    List<Activity> getActivityListByClueId(String id);

    List<Activity> getActivityListByNameAndNotRelation(Map<String, String> map);

    List<Activity> getActivityListByName(String aname);
}
