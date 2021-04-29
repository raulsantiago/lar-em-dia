package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AlterarEstadoAtendidoDTO {

    private Boolean ativo;
    private String  uf;

    public static AlterarEstadoAtendidoDTO create(EstadoAtendidoEntity estadoAtendidoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(estadoAtendidoEntity, AlterarEstadoAtendidoDTO.class);
    }

}
