package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.AgendaEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AlterarAgendaDTO {

    private Boolean   disponivel;

    public static AlterarAgendaDTO create(AgendaEntity agendaEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(agendaEntity, AlterarAgendaDTO.class);
    }
}
