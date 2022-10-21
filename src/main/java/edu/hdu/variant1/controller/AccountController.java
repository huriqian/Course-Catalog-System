package edu.hdu.variant1.controller;

import edu.hdu.variant1.bean.Message;
import edu.hdu.variant1.bean.Ret;
import edu.hdu.variant1.bean.SystemUser;
import edu.hdu.variant1.bean.User;
import edu.hdu.variant1.service.SystemUserService;
import edu.hdu.variant1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private SystemUserService sus;

    @Autowired
    private UserService us;

    @PostMapping("/register")
    public Ret userRegister(@RequestParam("user_id") String uId,
                            @RequestParam("password") String password) {
        Ret ret = new Ret();

        if (sus.doesAccountExist(uId)) { // if the account has existed, then return failure
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Account has existed", "0")});
        } else { // if not exist, then register the user
            Integer rows = sus.registerSysUser(uId, password);
            if (rows == 1) {
                us.setDefaultUser(uId, "user_" + uId);
                ret.setSuccessRet();
            } else {
                ret.setFailureRet();
                ret.setMessage(new Message[]{new Message("Register failed", "0")});
            }
        }
        return ret;
    }

    @PutMapping("/system/user/password")
    public Ret updatePassword(@RequestHeader("user_id") String uId,
                              @RequestParam("password") String password) {
        Ret ret = new Ret();
        Integer update = sus.updatePasswordByUserId(uId, password);

        if (update == 1) {
            ret.setSuccessRet();
        } else {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Update failed", "0")});
        }

        return ret;
    }

    @GetMapping("/user/query")
    public User getUser(@RequestHeader("user_id") String uId) {
        return us.getUserById(uId);
    }

    @GetMapping("/system/user")
    public List<SystemUser> getAllSystemUser() {
        return sus.getAllSystemUser();
    }

    @PutMapping("/system/user/identity")
    public Ret updateIdentity(@RequestHeader("target_id") String uId,
                              @RequestHeader("identity") Integer identity) {
        Ret ret = new Ret();
        Integer update = sus.updateIdentity(uId, identity);

        if (update == 1) {
            ret.setSuccessRet();
        } else {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Update failed", "0")});
        }

        return ret;
    }

    @PutMapping("/user/query")
    public Ret updateUserInfo(@RequestParam("user_id") String uId,
                              @RequestParam("user_name") String uName,
                              @RequestParam("gender") Integer gender,
                              @RequestParam("age") Integer age) {
        Ret ret = new Ret();
        Integer update = us.updateUser(uId, uName, gender, age);

        if (update == 1) {
            ret.setSuccessRet();
        } else {
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("Update failed", "0")});
        }
        return ret;
    }
}
