package com.github.shiwen.bedpod.common.exception;

/**
 * @author shiwen.wy
 * @date 2020/10/24 11:30 上午
 */
public class BedpodRuntimeException extends RuntimeException{
    public BedpodRuntimeException() {
    }

    public BedpodRuntimeException(String message) {
        super(message);
    }

    public BedpodRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BedpodRuntimeException(Throwable cause) {
        super(cause);
    }

}
