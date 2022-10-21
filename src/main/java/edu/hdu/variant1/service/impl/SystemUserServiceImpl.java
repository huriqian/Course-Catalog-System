package edu.hdu.variant1.service.impl;

import edu.hdu.variant1.bean.SystemUser;
import edu.hdu.variant1.mapper.SystemUserMapper;
import edu.hdu.variant1.service.SystemUserService;
import edu.hdu.variant1.utils.PasswordSHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserMapper sym;

    final PasswordSHAUtil SHAUtils = PasswordSHAUtil.getInstance();


    @Override
    public Integer registerSysUser(String uId, String password) {
        String code = SHAUtils.encodeSHA256(password);
        // Default : registering as a student and being activated
        SystemUser systemUser = new SystemUser(null, uId, code, 2, 1);
        return sym.registerSysUser(systemUser);
    }

    @Override
    public Boolean doesAccountExist(String uId) {
        // if account exists, then return true, else return false
        return sym.countAccountExisted(uId) != 0;
    }

    @Override
    public Boolean matchUserWithPassword(String uId, String password) {
        String DbPassword = sym.getPasswordByAccount(uId); // password in db, which encoded by SHA-256
        String encodedPassword = SHAUtils.encodeSHA256(password); // encoding input password
        return Objects.equals(DbPassword, encodedPassword);
    }

    @Override
    public Integer updatePasswordByUserId(String uId, String password) {
        String encodedPassword = SHAUtils.encodeSHA256(password); // encoding input password
        return sym.updatePasswordByUserId(uId, encodedPassword);
    }

    @Override
    public Integer getAuthIdentityByUserId(String uId) {
        return sym.getAuthIdentityByUserId(uId);
    }

    @Override
    public List<SystemUser> getAllSystemUser() {
        return sym.getAllSystemUser();
    }

    @Override
    public Integer updateIdentity(String uId, Integer identity) {
        return sym.updateSystemUserIdentity(uId, identity);
    }

}
