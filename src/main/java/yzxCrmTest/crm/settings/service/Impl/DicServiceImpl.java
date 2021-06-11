package yzxCrmTest.crm.settings.service.Impl;

import yzxCrmTest.crm.settings.dao.DicTypeDao;
import yzxCrmTest.crm.settings.dao.DicValueDao;
import yzxCrmTest.crm.settings.domain.DicType;
import yzxCrmTest.crm.settings.domain.DicValue;
import yzxCrmTest.crm.settings.service.DicService;
import yzxCrmTest.crm.utils.SqlSessionUtil;

import java.util.List;
import java.util.Map;

public class DicServiceImpl  implements DicService {
    private DicTypeDao dicTypeDao = SqlSessionUtil.getSqlSession().getMapper(DicTypeDao.class);
    private DicValueDao dicValueDao = SqlSessionUtil.getSqlSession().getMapper(DicValueDao.class);

    @Override
    public Map<String, List<DicValue>> getDV() {
        List<DicType>  dicTypeList = dicTypeDao.getTypeList();
        for (DicType dicType:dicTypeList){

        }
        return null;
    }
}
