package yzxCrmTest.crm.workbench.dao;

import yzxCrmTest.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int geCountById(String[] ids);
    int deleteById(String[] ids);
    List<ActivityRemark> getRemarkListByActivityId(String id);
    int deleteRemarkById(String id);
    int addRemark(ActivityRemark activityRemark);
    int updateRemark(ActivityRemark activityRemark);
}
