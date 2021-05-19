package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.AgendaEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
public class AgendaDTO {

    private Integer   idAgenda;
    private LocalDate dia;
    private String    turno;
    private Boolean   disponivel;

    public static AgendaDTO create(AgendaEntity agendaEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(agendaEntity, AgendaDTO.class);
    }

}
