package app.br.laremdia.service;

import app.br.laremdia.model.dto.*;
import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import app.br.laremdia.model.repository.EstadoAtendidoRepository;
import app.br.laremdia.model.repository.MunicipioAtendidoRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegiaoAtendidaService {

    @Autowired
    private EstadoAtendidoRepository estadoAtendidoRepository;

    @Autowired
    private MunicipioAtendidoRepository municipioAtendidoRepository;

    public List< EstadoAtendidoDTO > listarUf(){
        return estadoAtendidoRepository.findAll(Sort.by(Sort.Direction.ASC, "uf")).stream().map(EstadoAtendidoDTO::create).collect(Collectors.toList());
    }

    public List< EstadoAtendidoDTO > listarUfAtivo(){
        return estadoAtendidoRepository.ufAtivo(true).stream().map(EstadoAtendidoDTO::create).collect(Collectors.toList());
    }

    public List< MunicipioAtendidoDTO > listarMunicipo(){
        return municipioAtendidoRepository.findAll(Sort.by(Sort.Direction.ASC, "municipio")).stream().map(MunicipioAtendidoDTO::new).collect(Collectors.toList());
    }

    public List< MunicipioAtendidoDTO > listarMunicipioAtivoPorUfAtivo(Integer id){
        return municipioAtendidoRepository.listarMunicipioAtivoPorUfAtivo(id).stream().map(MunicipioAtendidoDTO::new).collect(Collectors.toList());
    }

    public MunicipioAtendidoDTO consultar(Integer id){
        Optional<MunicipioAtendidoEntity> municipioAtendidoEntity =  municipioAtendidoRepository.findById(id);
        return municipioAtendidoEntity.map(MunicipioAtendidoDTO::new).orElseThrow(() -> new BusinessException("Município não encontrado."));
    }


    public IncluirMunicipioAtendidoDTO inserir(IncluirMunicipioAtendidoDTO incluirMunicipioAtendidoDTO){
        Optional< EstadoAtendidoEntity > entityUf = estadoAtendidoRepository.findByUf(incluirMunicipioAtendidoDTO.getUf());
        Optional< MunicipioAtendidoEntity > entityMunicipio = municipioAtendidoRepository.findByMunicipio(incluirMunicipioAtendidoDTO.getMunicipio());
        if(entityUf.isPresent() && entityMunicipio.isPresent()){
            Assert.isTrue(false, "J\u00E1 existe este UF e municipio cadastrado com estes nomes.");
        }
        if(entityUf.isPresent()) {
            EstadoAtendidoEntity estadoAtendidoEntity =  entityUf.get();
            MunicipioAtendidoEntity novoMunicipio = new MunicipioAtendidoEntity();
            novoMunicipio.setMunicipio(incluirMunicipioAtendidoDTO.getMunicipio());
            novoMunicipio.setAtivo(incluirMunicipioAtendidoDTO.getAtivo());
            novoMunicipio.setEstado(estadoAtendidoEntity);
            return IncluirMunicipioAtendidoDTO.create(municipioAtendidoRepository.save(novoMunicipio));
        } else {
            EstadoAtendidoEntity novoEntityUf = new EstadoAtendidoEntity();
            novoEntityUf.setAtivo(true);
            novoEntityUf.setUf(incluirMunicipioAtendidoDTO.getUf());
            estadoAtendidoRepository.save(novoEntityUf);

            Optional< EstadoAtendidoEntity > novoUf = estadoAtendidoRepository.findByUf(incluirMunicipioAtendidoDTO.getUf());
            EstadoAtendidoEntity estadoAtendidoEntity = novoUf.get();
            MunicipioAtendidoEntity novoMunicipio = new MunicipioAtendidoEntity();
            novoMunicipio.setMunicipio(incluirMunicipioAtendidoDTO.getMunicipio());
            novoMunicipio.setAtivo(incluirMunicipioAtendidoDTO.getAtivo());
            novoMunicipio.setEstado(estadoAtendidoEntity);
            return IncluirMunicipioAtendidoDTO.create(municipioAtendidoRepository.save(novoMunicipio));
        }
    }

    public AlterarEstadoAtendidoDTO alterarEstado(EstadoAtendidoEntity estadoAtendidoEntity, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<EstadoAtendidoEntity> estado = estadoAtendidoRepository.findById(id);
        if(estado.isPresent()){
            EstadoAtendidoEntity estadoEntity = estado.get();
            estadoEntity.setAtivo(estadoAtendidoEntity.getAtivo());
            estadoEntity.setUf(estadoAtendidoEntity.getUf());
            estadoAtendidoRepository.save(estadoEntity);
            return AlterarEstadoAtendidoDTO.create(estadoEntity);
        } else {
            return null;
        }

    }

    public AlterarMunicipioAtendidoDTO alterarMunicipio(MunicipioAtendidoEntity municipioAtendidoEntity, Integer id){
        Assert.notNull(id, "Não foi possível atualizar o registro");
        Optional<MunicipioAtendidoEntity> municipio = municipioAtendidoRepository.findById(id);
        if(municipio.isPresent()){
            MunicipioAtendidoEntity municipioEntity = municipio.get();
            municipioEntity.setAtivo(municipioAtendidoEntity.getAtivo());
            municipioEntity.setMunicipio(municipioAtendidoEntity.getMunicipio());
            municipioAtendidoRepository.save(municipioEntity);
            return AlterarMunicipioAtendidoDTO.create(municipioEntity);
        } else {
            return null;
        }

    }

}
