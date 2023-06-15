package br.edu.ifsul.cstsi.springuber.corrida;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {
    @Query(value = "SELECT c FROM Corrida c where c.dataInicio = ?1")
    List<Corrida> findByDataInicio(LocalDate data_inicio);

    @Query(value = "SELECT p FROM Motorista c inner join Corrida p on c.id = p.motoristaByIdMotorista.id where c.id = ?1")
    List<Corrida> findByMotoristaByIdMotorista(Long id_motorista);

    @Query(value = "SELECT p FROM Usuario c inner join Corrida p on c.id = p.usuarioByIdUsuario.id where c.id = ?1")
    List<Corrida> findByUsuarioByIdUsuario(Long id_usuario);

}
