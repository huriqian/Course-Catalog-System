package edu.hdu.variant1.controller;

import edu.hdu.variant1.bean.Message;
import edu.hdu.variant1.bean.Ret;
import edu.hdu.variant1.service.SystemUserService;
import edu.hdu.variant1.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * LoginController is used for login and registering.
 */

@RestController
public class LoginController {
    private static final Integer VALID_TIME = 60 * 60 * 3; // Valid time is 3 hours

    @Autowired
    private SystemUserService sus;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    public Ret login(@RequestParam("user_id") String uId,
                     @RequestParam("password") String password) {
        Ret ret = new Ret();

        if (!isUserOnline(uId)) { // 0. if user is not online
            if (!sus.doesAccountExist(uId)) { // 1. if account does not exist
                ret.setFailureRet();
                Message message = new Message("The account does not exist", "0");
                ret.setMessage(new Message[]{message});
            } else { // 1. if account exists
                if (!sus.matchUserWithPassword(uId, password)) { // 2. if password is incorrect
                    ret.setFailureRet();
                    Message message = new Message("The account or password is incorrect", "0");
                    ret.setMessage(new Message[]{message});
                } else { // 2. if password correct
                    ret.setSuccessRet();
                    Message identity = new Message("identity", sus.getAuthIdentityByUserId(uId).toString());
                    Message authorization = setAuthCodeInRedis(uId);
                    ret.setMessage(new Message[]{identity, authorization});
                }
            }
        } else { // 1. if user is already online
            ret.setFailureRet();
            ret.setMessage(new Message[]{new Message("User now is online", "0")});
        }

        return ret;
    }

    @PostMapping("/logout")
    public void logout(@RequestParam("user_id") String uId,
                       @RequestParam("Authorization") String Authorization) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);

        redisUtil.del(uId);
    }



    private Message setAuthCodeInRedis(String uId) {
        Message message = new Message();

        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);

        String authCode = RedisUtil.getUUIDAuthCode();
        boolean set = redisUtil.set(uId, authCode, VALID_TIME);

        if (set) {
            message.setInfo("Authorization");
            message.setData(authCode);
        } else {
            message.setInfo("Initialize auth code failed");
            message.setData("0");
        }

        return message;
    }

    private Boolean isUserOnline(String uId) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);

        return redisUtil.hasKey(uId);
    }

}
