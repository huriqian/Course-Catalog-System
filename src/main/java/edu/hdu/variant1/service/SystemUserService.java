package edu.hdu.variant1.service;

import edu.hdu.variant1.bean.SystemUser;

import java.util.List;

public interface SystemUserService {

    Integer registerSysUser(String uId, String password);

    Boolean doesAccountExist(String uId);

    Boolean matchUserWithPassword(String uId, String password);

    Integer updatePasswordByUserId(String uId, String password);

    Integer getAuthIdentityByUserId(String uId);

    List<SystemUser> getAllSystemUser();

    Integer updateIdentity(String uId, Integer identity);
}
