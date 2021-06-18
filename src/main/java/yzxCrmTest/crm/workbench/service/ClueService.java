package yzxCrmTest.crm.workbench.service;

import yzxCrmTest.crm.vo.PaginationVO;
import yzxCrmTest.crm.workbench.domain.Clue;

import java.util.Map;

public interface ClueService {
    boolean save(Clue clue);

    PaginationVO<Clue> pageList(Map<String, Object> map);
}
