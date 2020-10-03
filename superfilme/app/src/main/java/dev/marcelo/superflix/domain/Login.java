package dev.marcelo.superflix.domain;

import java.util.HashMap;

import dev.marcelo.superflix.domain.exception.SenhaIncorretaException;
import dev.marcelo.superflix.domain.exception.UsuarioNaoEncontradoException;
import dev.marcelo.superflix.domain.model.Usuario;

public class Login {

    private static final Usuario USUARIO_NAO_ENCONTRADO = null;

    private static HashMap<String, Usuario> usuarios = new HashMap<>();

    static {
        Usuario adm = new Usuario("adm", "1234");

        usuarios.put(adm.getUsuario(), adm);
    }

    public static Usuario entrar(String usuario, String senha) throws UsuarioNaoEncontradoException, SenhaIncorretaException {
        Usuario usuarioBuscado = usuarios.get(usuario);

        if(usuarioBuscado == USUARIO_NAO_ENCONTRADO) throw new UsuarioNaoEncontradoException();

        if(!usuarioBuscado.getSenha().equals(senha)) throw new SenhaIncorretaException();

        return usuarioBuscado;
    }

}
