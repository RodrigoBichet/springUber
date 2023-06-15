package br.edu.ifsul.cstsi.springuber.corrida;

import br.edu.ifsul.cstsi.springuber.motorista.Motorista;
import br.edu.ifsul.cstsi.springuber.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import lombok.Data;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Corrida {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "tipo_pagamento", nullable = false, length = 255)
    private String tipoPagamento;
    @Basic
    @Column(name = "detalhes_pagamento", nullable = false, length = 255)
    private String detalhesPagamento;
    @Basic
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    @Basic
    @Column(name = "preco", nullable = false, precision = 0)
    private double preco;

    @ManyToOne
    @JoinColumn(name = "id_motorista", referencedColumnName = "id", nullable = false)
    private Motorista motoristaByIdMotorista;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuarioByIdUsuario;

    @Override
    public String toString() {
        return "\nCorrida{" +
                "id=" + id +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", detalhesPagamento='" + detalhesPagamento + '\'' +
                ", dataInicio=" + dataInicio +
                ", preco=" + preco +
                ", motoristaByIdMotorista=" + motoristaByIdMotorista +
                ", usuarioByIdUsuario=" + usuarioByIdUsuario +
                '}';
    }
}
