package com.github.welblade.bancodigital.core.exception;

public abstract class BancoDigitalException extends Exception {
    BancoDigitalException(String message){
        super(message);
    }
}
