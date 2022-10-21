package edu.hdu.variant1.interceptor;

import edu.hdu.variant1.utils.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Check a guest is login or not.
 * If yes, permit it through.
 * If no, intercept the request and need it to login.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * All we do is to implement the login logic.
     * @param request : current HTTP request
     * @param response : current HTTP response
     * @param handler : chosen handler to execute, for type and/or instance evaluation
     * @return : permission to get through
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);

        String uId = request.getHeader("user_id");

        if (StringUtils.isEmpty(uId)) {
            return false;
        } else {
            if (!redisUtil.hasKey(uId)) {
                response.addHeader("info", "intercepted");
                return false;
            } else {
                if (!redisUtil.get(uId).equals(request.getHeader("Authorization"))) {
                    response.setHeader("info", "intercepted");
                    return false;
                } else {
                    return true;
                }
            }
        }
    }
}
