package yzxCrmTest.crm.settings.dao;

import yzxCrmTest.crm.settings.domain.DicValue;

import java.util.List;

public interface DicValueDao {
    List<DicValue> getListByCode(String code);
}
