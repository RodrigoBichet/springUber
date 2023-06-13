package br.edu.ifsul.cstsi.springuber.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query(value = "SELECT c FROM Usuario c where c.nome like ?1")
    List<Usuario> findByNome(String nome);



}

