package com.grupo3.superflix.exception;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("Senha invalida.");
    }

}