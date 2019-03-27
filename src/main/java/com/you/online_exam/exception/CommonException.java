package com.you.online_exam.exception;

/**
 * CommonException
 *
 * @author 尤广富 (Online Class)
 * @create 2019-03-27 10:55
 **/
public class CommonException extends  RuntimeException{
    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }
}
