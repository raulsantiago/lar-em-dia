package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class EstadoAtendidoDTO {

    private Integer idEstado;
    private String  uf;
    private Boolean ativo;

    public static EstadoAtendidoDTO create(EstadoAtendidoEntity estadoAtendidoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(estadoAtendidoEntity, EstadoAtendidoDTO.class);
    }


}
