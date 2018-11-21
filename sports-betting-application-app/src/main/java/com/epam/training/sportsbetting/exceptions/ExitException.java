package com.epam.training.sportsbetting.exceptions;

public class ExitException extends Exception{
    public static final String OK = "OK";

    public ExitException(String message) {
        super(message);
    }
}
