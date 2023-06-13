package br.edu.ifsul.cstsi.springuber.motorista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MotoristaRepository extends JpaRepository<Motorista,Long> {

    @Query(value = "SELECT c FROM Motorista c where c.nome like ?1")
    List<Motorista> findByNome(String nome);



}

