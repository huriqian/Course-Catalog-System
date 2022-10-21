package edu.hdu.variant1.mapper;

import edu.hdu.variant1.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface UserMapper {
    User getUserById(String uId);

    Integer insertDefaultUser(String uId, String uName, Date date);

    Integer updateUserByUserId(User user);
}
