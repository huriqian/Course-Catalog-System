package edu.hdu.variant1.bean;

import edu.hdu.variant1.utils.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ret is a message class to transmit information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ret {
    private Integer code; // Status code
    private String codeInfo;
    private Message[] message;

    public void setSuccessRet() {
        this.code = Status.SUCCESS.CODE;
        this.codeInfo = Status.SUCCESS.INFO;
    }

    public void setFailureRet() {
        this.code = Status.FAILURE.CODE;
        this.codeInfo = Status.FAILURE.INFO;
    }
}



