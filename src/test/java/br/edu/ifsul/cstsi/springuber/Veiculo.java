package br.edu.ifsul.cstsi.springuber;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
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
    private Date anoFabricacao;
    @Basic
    @Column(name = "id_motorista", nullable = false)
    private long idMotorista;
    @ManyToOne
    @JoinColumn(name = "id_motorista", referencedColumnName = "id", nullable = false)
    private Motorista motoristaByIdMotorista;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Date anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Veiculo veiculo = (Veiculo) o;

        if (id != veiculo.id) return false;
        if (tipo != null ? !tipo.equals(veiculo.tipo) : veiculo.tipo != null) return false;
        if (placa != null ? !placa.equals(veiculo.placa) : veiculo.placa != null) return false;
        if (anoFabricacao != null ? !anoFabricacao.equals(veiculo.anoFabricacao) : veiculo.anoFabricacao != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (placa != null ? placa.hashCode() : 0);
        result = 31 * result + (anoFabricacao != null ? anoFabricacao.hashCode() : 0);
        return result;
    }

    public long getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(long idMotorista) {
        this.idMotorista = idMotorista;
    }

    public Motorista getMotoristaByIdMotorista() {
        return motoristaByIdMotorista;
    }

    public void setMotoristaByIdMotorista(Motorista motoristaByIdMotorista) {
        this.motoristaByIdMotorista = motoristaByIdMotorista;
    }
}
