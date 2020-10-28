package com.grupo3.superflix.domain;

import android.content.Context;

import com.grupo3.superflix.data.database.FactoryDataBase;
import com.grupo3.superflix.data.database.dao.UsuarioDAO;
import com.grupo3.superflix.data.model.Usuario;
import com.grupo3.superflix.exception.SenhaIncorretaException;
import com.grupo3.superflix.exception.UsuarioNaoEncontradoException;

public class Login {

    private static final Usuario USUARIO_NAO_ENCONTRADO = null;

    private UsuarioDAO usuarioDAO;


    public Login(Context context) {
        usuarioDAO = FactoryDataBase.getInstanceAppDataBase(context).getUsuarioDAO();

        Usuario adm = new Usuario("adm", "1234");

        if(usuarioDAO.buscarPorUsuario("adm") == null) {
            usuarioDAO.salvar(adm);
        }

    }

    public Usuario entrar(String usuario, String senha) throws UsuarioNaoEncontradoException, SenhaIncorretaException {
        Usuario usuarioBuscado = usuarioDAO.buscarPorUsuario(usuario);

        if(usuarioBuscado == USUARIO_NAO_ENCONTRADO) throw new UsuarioNaoEncontradoException();

        if(!usuarioBuscado.getSenha().equals(senha)) throw new SenhaIncorretaException();

        return usuarioBuscado;
    }

}
