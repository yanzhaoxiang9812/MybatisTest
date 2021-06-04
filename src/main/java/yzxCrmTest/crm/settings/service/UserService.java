package yzxCrmTest.crm.settings.service;

import yzxCrmTest.crm.exception.LoginExeption;
import yzxCrmTest.crm.settings.domian.User;

public interface UserService {
    User Login(String username, String pwd, String ip) throws LoginExeption;
}
