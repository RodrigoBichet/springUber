package br.edu.ifsul.cstsi.springuber.veiculo;

import br.edu.ifsul.cstsi.springuber.motorista.Motorista;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import lombok.Data;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "tipo", nullable = false, length = 255)
    private String tipo;
    @Basic
    @Column(name = "placa", nullable = false, length = 255)
    private String placa;
    @Basic
    @Column(name = "ano_fabricacao", nullable = false)
    private LocalDate anoFabricacao;
//    @Basic
//    @Column(name = "id_motorista", nullable = false)
//    private long idMotorista;
    @ManyToOne
    @JoinColumn(name = "id_motorista", referencedColumnName = "id", nullable = false)
    private Motorista motoristaByIdMotorista;

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", placa='" + placa + '\'' +
                ", anoFabricacao=" + anoFabricacao +
                ", motoristaByIdMotorista=" + motoristaByIdMotorista +
                '}';
    }
}
