package dev.marcelo.superflix.domain.exception;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("Senha invalida.");
    }

}
