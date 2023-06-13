package br.edu.ifsul.cstsi.springuber.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private br.edu.ifsul.cstsi.springuber.usuario.UsuarioRepository rep;

    public List<Usuario> getUsuarios() {
        return rep.findAll();
    }


    public Usuario getUsuarioByIdUsuario(Long idUsuario) {
        Optional<Usuario> optional = rep.findById(idUsuario);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Usuario> getUsuariosByNome(String nome) {
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }



    public Usuario insert(Usuario usuario) {
        //Assert.isNull(usuario.getId(),"Não foi possível inserir o registro");
        return rep.save(usuario);
    }

    public Usuario update(Usuario usuario) {
        Assert.notNull(usuario.getId(),"Não foi possível atualizar o registro");


        Optional<Usuario> optional = rep.findById(usuario.getId());
        if(optional.isPresent()) {
            Usuario db = optional.get();
            db.setNome(usuario.getNome());
            db.setEmail(usuario.getEmail());
            db.setTelefone(usuario.getTelefone());
            rep.save(db);
            return db;
        } else {
            return null;

        }
    }

    public void delete(Long idUsuario) {
        rep.deleteById(idUsuario);
    }
}








