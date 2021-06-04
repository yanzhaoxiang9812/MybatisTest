package yzxCrmTest.crm.settings.service.Impl;

import yzxCrmTest.crm.settings.dao.UserDao;
import yzxCrmTest.crm.settings.service.UserService;
import yzxCrmTest.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
            
}
