package yzxCrmTest.crm.workbench.service.Impl;

import yzxCrmTest.crm.utils.SqlSessionUtil;
import yzxCrmTest.crm.workbench.dao.ActivityDao;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao  = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    public boolean save(Activity activity) {
        boolean flag = true;
        int count = activityDao.save(activity);
        if(count!=1){

            flag = false;

        }
        return flag;
    }
}
