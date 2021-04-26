package app.br.laremdia.service;

import app.br.laremdia.model.dto.ServicoProfissionalDTO;
import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import app.br.laremdia.model.repository.ServicoProfissionalRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoProfissionalService {

    @Autowired
    private ServicoProfissionalRepository servicoProfissionalRepository;

    public List< ServicoProfissionalDTO > listar(){
        return servicoProfissionalRepository.findAll(Sort.by(Sort.Direction.ASC, "nome")).stream().map(ServicoProfissionalDTO::create).collect(Collectors.toList());
    }

    public ServicoProfissionalDTO consultar(Integer id){
        Optional< ServicoProfissionalEntity > servicoProfissionalEntity =  servicoProfissionalRepository.findById(id);
        return servicoProfissionalEntity.map(ServicoProfissionalDTO::create).orElseThrow(() -> new BusinessException("Serviço profissional não encontrado."));
    }

    public ServicoProfissionalDTO consultarNome(String nome){
        Optional< ServicoProfissionalEntity > servicoProfissionalEntity =  servicoProfissionalRepository.findByNome(nome);
        return servicoProfissionalEntity.map(ServicoProfissionalDTO::create).orElseThrow(() -> new BusinessException("Serviço profissional não encontrado."));
    }

    public ServicoProfissionalDTO inserir(ServicoProfissionalEntity servicoProfissionalEntity){
        String name = servicoProfissionalEntity.getNome();
        Optional< ServicoProfissionalEntity > entity = servicoProfissionalRepository.findByNome(name);
        if(entity.isPresent()){
            Assert.isTrue(false, "J\u00E1 existe serviço cadastrado com este nome.");
        }
        return ServicoProfissionalDTO.create(servicoProfissionalRepository.save(servicoProfissionalEntity));
    }

    public ServicoProfissionalDTO alterar(ServicoProfissionalEntity servicoProfissionalEntity, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<ServicoProfissionalEntity> servico = servicoProfissionalRepository.findById(id);
        if(servico.isPresent()){
            ServicoProfissionalEntity servicoProfissional = servico.get();
            servicoProfissional.setNome(servicoProfissionalEntity.getNome());
            servicoProfissional.setAtivo(servicoProfissionalEntity.getAtivo());
            servicoProfissionalRepository.save(servicoProfissional);
            return ServicoProfissionalDTO.create(servicoProfissional);
        }else {
            return null;
        }
    }

}
