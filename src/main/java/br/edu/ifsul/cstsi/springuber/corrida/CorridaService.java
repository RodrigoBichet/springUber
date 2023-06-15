package br.edu.ifsul.cstsi.springuber.corrida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CorridaService {
    @Autowired
    private br.edu.ifsul.cstsi.springuber.corrida.CorridaRepository rep;

    public List<Corrida> getCorridas() {
        return rep.findAll();
    }


    public Corrida getCorridaByIdCorrida(Long idCorrida) {
        Optional<Corrida> optional = rep.findById(idCorrida);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Corrida> getCorridaByDataInicio(LocalDate data_inicio) {
        return new ArrayList<>(rep.findByDataInicio(data_inicio));
    }

    public List<Corrida> getCorridaByMotoristaByIdMotorista(Long id_motorista) {
        return new ArrayList<>(rep.findByMotoristaByIdMotorista(id_motorista));
    }


    public List<Corrida> getCorridaByUsuarioByIdUsuario(Long id_usuario) {
        return new ArrayList<>(rep.findByUsuarioByIdUsuario(id_usuario));
    }
    public Corrida insert(Corrida corrida) {
        //Assert.isNull(corrida.getId(),"Não foi possível inserir o registro");
        return rep.save(corrida);
    }

    public Corrida update(Corrida corrida) {
        Assert.notNull(corrida.getId(),"Não foi possível atualizar o registro");
        Optional<Corrida> optional = rep.findById(corrida.getId());
        if(optional.isPresent()) {
            Corrida db = optional.get();
            db.setTipoPagamento(corrida.getTipoPagamento());
            db.setDetalhesPagamento(corrida.getDetalhesPagamento());
            db.setDataInicio(corrida.getDataInicio());
            db.setPreco(corrida.getPreco());
            db.setMotoristaByIdMotorista((corrida.getMotoristaByIdMotorista()));
            db.setUsuarioByIdUsuario((corrida.getUsuarioByIdUsuario()));
            rep.save(db);
            return db;
        } else {
            return null;

        }
    }

    public void delete(Long idCorrida) {
        rep.deleteById(idCorrida);
    }
}

