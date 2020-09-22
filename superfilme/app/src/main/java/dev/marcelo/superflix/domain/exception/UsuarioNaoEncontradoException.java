package dev.marcelo.superflix.domain.exception;

public class UsuarioNaoEncontradoException extends Exception {

    public UsuarioNaoEncontradoException() {
        super("Usuario nao encontrado.");
    }

}
