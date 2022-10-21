package edu.hdu.variant1.mapper;

import edu.hdu.variant1.bean.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemUserMapper {
    Integer registerSysUser(SystemUser systemUser);

    Integer countAccountExisted(String uId);

    String getPasswordByAccount(String uId);

    Integer updatePasswordByUserId(String uId, String password);

    Integer getAuthIdentityByUserId(String uId);

    List<SystemUser> getAllSystemUser();

    Integer updateSystemUserIdentity(String uId, Integer identity);
}
