package app.br.laremdia.service;

import app.br.laremdia.model.dto.AgendaDTO;
import app.br.laremdia.model.entity.AgendaEntity;
import app.br.laremdia.model.repository.AgendaRepository;
import app.br.laremdia.rest.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public List<AgendaDTO> listar(){
        return agendaRepository.findAll().stream().map(AgendaDTO::create).collect(Collectors.toList());
    }

    public AgendaDTO consultar(Integer id) {
        Optional< AgendaEntity > agendaEntity = agendaRepository.findById(id);
        return agendaEntity.map(AgendaDTO::create).orElseThrow(() -> new BusinessException("Agenda não encontrada."));
    }

    public AgendaDTO inserir(AgendaEntity agendaEntity){
        return AgendaDTO.create(agendaRepository.save(agendaEntity));
    }

    public void excluir(Integer id){
        Optional<AgendaEntity> agendaEntity = agendaRepository.findById(id);
        agendaRepository.findById(id).map( agenda -> {
            agendaRepository.delete(agenda);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda não encontrada."));

    }

}
