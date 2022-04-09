package com.example.MyTwitter.exceptions;

public class NullArgumentException extends RuntimeException{
    public NullArgumentException() {
        super("Проверьте вводимые данные");
    }
}
