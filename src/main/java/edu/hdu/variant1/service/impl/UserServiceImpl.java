package edu.hdu.variant1.service.impl;

import edu.hdu.variant1.bean.User;
import edu.hdu.variant1.mapper.UserMapper;
import edu.hdu.variant1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper um;

    @Override
    public User getUserById(String uId) {
        return um.getUserById(uId);
    }

    @Override
    public void setDefaultUser(String uId, String userName) {
        um.insertDefaultUser(uId, userName, new Date());
    }

    @Override
    public Integer updateUser(String uId, String uName, Integer gender, Integer age) {
        User newUser = new User();
        newUser.setUId(uId);
        newUser.setUName(uName);
        newUser.setGender(gender);
        newUser.setAge(age);
        newUser.setCreated(new Date());
        return um.updateUserByUserId(newUser);
    }


}
