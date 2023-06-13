package br.edu.ifsul.cstsi.springuber.veiculo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private br.edu.ifsul.cstsi.springuber.veiculo.VeiculoRepository rep;

    public List<Veiculo> getVeiculos() {
        return rep.findAll();
    }


    public Veiculo getVeiculoByIdVeiculo(Long idVeiculo) {
        Optional<Veiculo> optional = rep.findById(idVeiculo);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Veiculo> getVeiculosByTipo(String tipo) {
        return new ArrayList<>(rep.findByTipo(tipo + "%"));
       }

    public List<Veiculo> getVeiculosByPlaca(String placa) {
        return new ArrayList<>(rep.findByPlaca(placa + "%"));
    }

    public Veiculo insert(Veiculo veiculo) {
        //Assert.isNull(veiculo.getId(),"Não foi possível inserir o registro");
        return rep.save(veiculo);
    }




    public Veiculo update(Veiculo veiculo) {
        Assert.notNull(veiculo.getId(),"Não foi possível atualizar o registro");


        Optional<Veiculo> optional = rep.findById(veiculo.getId());
        if(optional.isPresent()) {
            Veiculo db = optional.get();
            db.setTipo(veiculo.getTipo());
            db.setPlaca(veiculo.getPlaca());
            db.setAnoFabricacao(veiculo.getAnoFabricacao());
            rep.save(db);
            return db;
        } else {
            return null;

        }
    }

    public void delete(Long idVeiculo) {
        rep.deleteById(idVeiculo);
    }
}
