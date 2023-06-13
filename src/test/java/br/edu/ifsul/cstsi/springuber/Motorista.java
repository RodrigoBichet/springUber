package br.edu.ifsul.cstsi.springuber;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Motorista motorista = (Motorista) o;

        if (id != motorista.id) return false;
        if (idVeiculo != motorista.idVeiculo) return false;
        if (nome != null ? !nome.equals(motorista.nome) : motorista.nome != null) return false;
        if (email != null ? !email.equals(motorista.email) : motorista.email != null) return false;
        if (telefone != null ? !telefone.equals(motorista.telefone) : motorista.telefone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefone != null ? telefone.hashCode() : 0);
        result = 31 * result + (int) (idVeiculo ^ (idVeiculo >>> 32));
        return result;
    }

    public Collection<Corrida> getCorridasById() {
        return corridasById;
    }

    public void setCorridasById(Collection<Corrida> corridasById) {
        this.corridasById = corridasById;
    }

    public Collection<Veiculo> getVeiculosById() {
        return veiculosById;
    }

    public void setVeiculosById(Collection<Veiculo> veiculosById) {
        this.veiculosById = veiculosById;
    }
}
