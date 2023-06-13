package br.edu.ifsul.cstsi.springuber.veiculo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {

    @Query(value = "SELECT c FROM Veiculo c where c.tipo like ?1")
    List<Veiculo> findByTipo(String tipo);

    @Query(value = "SELECT c FROM Veiculo c where c.placa like ?1")
    List<Veiculo> findByPlaca(String placa);

}
