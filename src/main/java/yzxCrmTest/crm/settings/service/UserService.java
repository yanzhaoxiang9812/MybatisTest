package yzxCrmTest.crm.settings.service;

import yzxCrmTest.crm.exception.LoginExeption;
import yzxCrmTest.crm.settings.domain.User;

import java.util.List;

public interface UserService {
    User Login(String username, String pwd, String ip) throws LoginExeption;
    List<User> getUserList();
}
