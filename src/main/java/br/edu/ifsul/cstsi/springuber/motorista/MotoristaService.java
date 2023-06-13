package br.edu.ifsul.cstsi.springuber.motorista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository rep;

    public  List<Motorista> getMotoristas() {
        return rep.findAll();
    }


    public Motorista getMotoristaByIdMotorista(Long idMotorista) {
        Optional<Motorista> optional = rep.findById(idMotorista);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public  List<Motorista> getMotoristasByNome(String nome) {
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }



    public Motorista insert(Motorista motorista) {
        //Assert.isNull(motorista.getId(),"Não foi possível inserir o registro");
        return rep.save(motorista);
    }



    public Motorista update(Motorista motorista) {
        Assert.notNull(motorista.getId(),"Não foi possível atualizar o registro");


        Optional<Motorista> optional = rep.findById(motorista.getId());
        if(optional.isPresent()) {
            Motorista db = optional.get();
            db.setNome(motorista.getNome());
            db.setEmail(motorista.getEmail());
            db.setTelefone(motorista.getTelefone());
            rep.save(db);
            return db;
        } else {
            return null;

        }
    }

    public void delete(Long idMotorista) {
        rep.deleteById(idMotorista);
    }
}








