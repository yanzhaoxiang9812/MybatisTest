package yzxCrmTest.crm.workbench.service.Impl;

import yzxCrmTest.crm.settings.dao.UserDao;
import yzxCrmTest.crm.settings.domain.User;
import yzxCrmTest.crm.utils.SqlSessionUtil;
import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.dao.ActivityDao;
import yzxCrmTest.crm.workbench.dao.ActivityRemarkDao;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.domain.ActivityRemark;
import yzxCrmTest.crm.workbench.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImpl implements ActivityService {
    private ActivityDao activityDao  = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    private ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
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

    @Override
    public boolean delete(String[] ids) {
        boolean flag = true;
        //查询出需要删除的备注的数量
        int count1 = activityRemarkDao.geCountById(ids);
        //删除备注，返回影响条数
        int count2 = activityRemarkDao.deleteById(ids);
        if (count1!=count2){
            flag=false;
        }
        //删除活动
        int count3 = activityDao.deleteById(ids);
        if (count3!= ids.length){
            flag=false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {
        //取uList
        List<User> uList = userDao.getUserList();

        //取Activity
        Activity a = activityDao.getActivityById(id);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("a",a);

        return map;
    }

    @Override
    public boolean update(Activity a) {
        boolean flag = true;
        int count = activityDao.update(a);
        if(count!=1){

            flag = false;

        }
        return flag;
    }

    @Override
    public Activity detail(String id) {
        Activity a = activityDao.detail(id);
        return a;
    }

    @Override
    public List<ActivityRemark> getRemarkListByActivityId(String id) {
        List<ActivityRemark> activityRemarkList = activityRemarkDao.getRemarkListByActivityId(id);
        return activityRemarkList;
    }

    @Override
    public boolean deleteRemarkById(String id) {
        boolean flag = true;
        int count = activityRemarkDao.deleteRemarkById(id);
        if(count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean addRemark(ActivityRemark activityRemark) {
        boolean flag = true;
        int count = activityRemarkDao.addRemark(activityRemark);
        if(count!=1){

            flag = false;

        }
        return flag;
    }

    @Override
    public boolean updateRemark(ActivityRemark activityRemark) {
        boolean flag = true;
        int count = activityRemarkDao.updateRemark(activityRemark);
        if(count!=1){

            flag = false;

        }
        return flag;
    }

    @Override
    public List<Activity> getActivityListByClueId(String id) {
        List<Activity> aList = activityDao.getActivityListByClueId(id);
        return aList;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotRelation(Map<String, String> map) {
        List<Activity> aList = activityDao.getActivityListByNameAndNotRelation(map);
        return aList;
    }

    @Override
    public List<Activity> getActivityListByName(String aname) {
        List<Activity> aList = activityDao.getActivityListByName(aname);
        return aList;
    }
}
