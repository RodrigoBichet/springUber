package br.edu.ifsul.cstsi.springuber.motorista;

import br.edu.ifsul.cstsi.springuber.corrida.Corrida;
import br.edu.ifsul.cstsi.springuber.veiculo.Veiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorista {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "telefone", nullable = false, length = 255)
    private String telefone;
    @Basic
    @Column(name = "idVeiculo", nullable = false)
    private long idVeiculo;
    @OneToMany(mappedBy = "motoristaByIdMotorista")
    private Collection<Corrida> corridasById;
    @OneToMany(mappedBy = "motoristaByIdMotorista")
    private Collection<Veiculo> veiculosById;

    @Override
    public String toString() {
        return "Motorista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", idVeiculo=" + idVeiculo +
                '}';
    }
}
