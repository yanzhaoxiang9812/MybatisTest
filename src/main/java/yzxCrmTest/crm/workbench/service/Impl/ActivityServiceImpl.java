package yzxCrmTest.crm.workbench.service.Impl;

import yzxCrmTest.crm.utils.SqlSessionUtil;
import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.dao.ActivityDao;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.service.ActivityService;

import java.util.List;
import java.util.Map;

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

    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        //取得total
        int total = activityDao.getTotalByCondition(map);
        //取得dataList
        List<Activity> dataList = activityDao.getActivityListByCondition(map);
        //创建一个vo对象，将total和dataList封装到vo中
        PaginationVO<Activity> vo = new PaginationVO<Activity>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        //将vo返回
        return vo;
    }
}
