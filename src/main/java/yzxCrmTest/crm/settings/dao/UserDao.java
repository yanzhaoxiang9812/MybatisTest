package yzxCrmTest.crm.settings.dao;

import yzxCrmTest.crm.settings.domian.User;
import java.util.Map;

public interface UserDao {
    User Login(Map<String, String> map);
}
