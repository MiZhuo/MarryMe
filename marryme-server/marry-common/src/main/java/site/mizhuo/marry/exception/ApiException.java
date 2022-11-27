package site.mizhuo.marry.exception;

import site.mizhuo.marry.common.IErrorCode;

/**
 * 自定义API异常
 *
 * @author macro
 * @date 2020/2/27
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -991112344362563600L;

    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
