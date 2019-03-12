package com.nuanshui.frms.test.exception;

/**
 *  自定义一个Runtime Exception 异常
 * Created by xingshi on 2017/07/26.
 *
 */
public class MessageRuntimeException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 4777385284072984532L;

    public MessageRuntimeException(String message) {
        super(message);
    }
}
