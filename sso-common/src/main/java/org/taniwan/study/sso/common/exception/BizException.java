package org.taniwan.study.sso.common.exception;

/**
 * 系统业务异常，不建议继承该异常派生出自己的异常（dubbo调用时不好处理），可以通过errorCode来标识自己的身份。
 * @author Chao Zhang
 * @date 2017/1/23-16:48
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 4614805988759009856L;

    private BizErrorCode errorCode;

    public BizException() {}

    public BizException(BizErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BizErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "BizException{" +
                "errorCode=" + errorCode +
                '}';
    }
}
