package yzxCrmTest.crm.settings.service;

import yzxCrmTest.crm.settings.domain.DicValue;

import java.util.List;
import java.util.Map;

public interface DicService {
    Map<String, List<DicValue>> getDV();
}
