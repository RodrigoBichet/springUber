package br.edu.ifsul.cstsi.springuber;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
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
    private Date dataInicio;
    @Basic
    @Column(name = "preco", nullable = false, precision = 0)
    private double preco;
    @Basic
    @Column(name = "id_usuario", nullable = false)
    private long idUsuario;
    @Basic
    @Column(name = "id_motorista", nullable = false)
    private long idMotorista;
    @ManyToOne
    @JoinColumn(name = "id_motorista", referencedColumnName = "id", nullable = false)
    private Motorista motoristaByIdMotorista;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    private Usuario usuarioByIdUsuario;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getDetalhesPagamento() {
        return detalhesPagamento;
    }

    public void setDetalhesPagamento(String detalhesPagamento) {
        this.detalhesPagamento = detalhesPagamento;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(long idMotorista) {
        this.idMotorista = idMotorista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Corrida corrida = (Corrida) o;

        if (id != corrida.id) return false;
        if (Double.compare(corrida.preco, preco) != 0) return false;
        if (idUsuario != corrida.idUsuario) return false;
        if (idMotorista != corrida.idMotorista) return false;
        if (tipoPagamento != null ? !tipoPagamento.equals(corrida.tipoPagamento) : corrida.tipoPagamento != null)
            return false;
        if (detalhesPagamento != null ? !detalhesPagamento.equals(corrida.detalhesPagamento) : corrida.detalhesPagamento != null)
            return false;
        if (dataInicio != null ? !dataInicio.equals(corrida.dataInicio) : corrida.dataInicio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tipoPagamento != null ? tipoPagamento.hashCode() : 0);
        result = 31 * result + (detalhesPagamento != null ? detalhesPagamento.hashCode() : 0);
        result = 31 * result + (dataInicio != null ? dataInicio.hashCode() : 0);
        temp = Double.doubleToLongBits(preco);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (idUsuario ^ (idUsuario >>> 32));
        result = 31 * result + (int) (idMotorista ^ (idMotorista >>> 32));
        return result;
    }

    public Motorista getMotoristaByIdMotorista() {
        return motoristaByIdMotorista;
    }

    public void setMotoristaByIdMotorista(Motorista motoristaByIdMotorista) {
        this.motoristaByIdMotorista = motoristaByIdMotorista;
    }

    public Usuario getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(Usuario usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }
}
