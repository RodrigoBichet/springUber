package br.edu.ifsul.cstsi.springuber.usuario;

import br.edu.ifsul.cstsi.springuber.corrida.Corrida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
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
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<Corrida> corridasById;

    @Override
    public String toString() {
        return "\nUsuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
