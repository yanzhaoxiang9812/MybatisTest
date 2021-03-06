package yzxCrmTest.crm.workbench.service.Impl;

import yzxCrmTest.crm.utils.SqlSessionUtil;
import yzxCrmTest.crm.utils.UUIDUtil;
import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.dao.ClueActivityRelationDao;
import yzxCrmTest.crm.workbench.dao.ClueDao;
import yzxCrmTest.crm.workbench.domain.Activity;
import yzxCrmTest.crm.workbench.domain.Clue;
import yzxCrmTest.crm.workbench.domain.ClueActivityRelation;
import yzxCrmTest.crm.workbench.service.ClueService;

import java.util.List;
import java.util.Map;

public class ClueServiceImpl implements ClueService {
    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    @Override
    public boolean save(Clue clue) {
        boolean flag = true;
        int count = clueDao.save(clue);
        if(count!=1){

            flag = false;

        }
        return flag;
    }

    @Override
    public PaginationVO<Clue> pageList(Map<String, Object> map) {
        //取得total
        int total = clueDao.getTotalByCondition(map);
        //取得dataList
        List<Clue> dataList = clueDao.getClueListByCondition(map);
        //创建一个vo对象，将total和dataList封装到vo中
        PaginationVO<Clue> vo = new PaginationVO<Clue>();
        vo.setTotal(total);
        vo.setDataList(dataList);
        //将vo返回
        return vo;
    }

    @Override
    public Clue detail(String id) {
        Clue c = clueDao.detail(id);
        return c;
    }

    @Override
    public boolean unbund(String id) {
        boolean flag = true;
        int count = clueActivityRelationDao.unbund(id);
        if(count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean bund(String cid, String[] aids) {
        boolean flag = true;
        for (String aid : aids){
            ClueActivityRelation car = new ClueActivityRelation(
                    UUIDUtil.getUUID(),
                    cid,
                    aid);
            int count = clueActivityRelationDao.bund(car);
            if(count!=1){
                flag = false;
            }
        }
        return flag;
    }
}
