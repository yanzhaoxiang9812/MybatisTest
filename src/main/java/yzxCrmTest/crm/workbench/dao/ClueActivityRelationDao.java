package yzxCrmTest.crm.workbench.dao;


import yzxCrmTest.crm.workbench.domain.ClueActivityRelation;

public interface ClueActivityRelationDao {


    int unbund(String id);

    int bund(ClueActivityRelation car);
}
