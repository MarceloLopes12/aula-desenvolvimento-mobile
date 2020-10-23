package dev.marcelo.superflix.exception;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("Senha invalida.");
    }

}
