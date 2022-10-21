package edu.hdu.variant1.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
    private Integer id;
    private String account;
    private String password;
    /**
     * identity refers to the identity of user
     * 0 : administrator
     * 1 : teacher
     * 2 : student
     */
    private Integer identity;
    /**
     * status refers to the activated condition of user
     * 1 : activated and ready to use
     * 0 : have registered but unactivated
     * -1 : account is frozen or could not be used
     */
    private Integer status;
}
