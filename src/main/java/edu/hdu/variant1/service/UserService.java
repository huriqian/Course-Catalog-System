package edu.hdu.variant1.service;

import edu.hdu.variant1.bean.User;

public interface UserService {
    User getUserById(String uId);

    void setDefaultUser(String uId, String userName);

    Integer updateUser(String uId, String uName, Integer gender, Integer age);
}
