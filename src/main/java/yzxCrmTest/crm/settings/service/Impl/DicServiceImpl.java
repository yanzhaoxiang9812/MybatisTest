package yzxCrmTest.crm.settings.service.Impl;

import yzxCrmTest.crm.settings.dao.DicTypeDao;
import yzxCrmTest.crm.settings.dao.DicValueDao;
import yzxCrmTest.crm.settings.service.DicService;
import yzxCrmTest.crm.utils.SqlSessionUtil;

public class DicServiceImpl  implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);
}
