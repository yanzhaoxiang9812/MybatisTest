package yzxCrmTest.crm.settings.service.Impl;

import yzxCrmTest.crm.exception.LoginExeption;
import yzxCrmTest.crm.settings.dao.UserDao;
import yzxCrmTest.crm.settings.domian.User;
import yzxCrmTest.crm.settings.service.UserService;
import yzxCrmTest.crm.utils.DateTimeUtil;
import yzxCrmTest.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public User Login(String loginAct, String loginPwd, String ip) throws LoginExeption {
        Map<String,String> map = new HashMap<String, String>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        //判断账号密码
        User user = userDao.Login(map);
        if (user==null){
            //账号密码不存在
            throw new LoginExeption("账号或密码错误");
        }

        //验证账号失效时间
        /*
        String exprieTime = user.getCreateTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (exprieTime.compareTo(currentTime) < 0){
            //账号失效
        }

         */

        //判断是否锁定
        String lokState = user.getLockState();
        if ("0".equals(lokState)){
            throw new LoginExeption("账号已锁定");
        }

        //判断ip地址
        /*
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)){
            throw new LoginExeption("ip地址受限");
        }

         */


        return user;
    }
}
