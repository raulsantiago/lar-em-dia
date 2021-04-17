package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ServicoProfissionalDTO {

    private Integer idServico;
    private String  nome;
    private Boolean ativo;

    public static ServicoProfissionalDTO create(ServicoProfissionalEntity servicoProfissionalEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(servicoProfissionalEntity, ServicoProfissionalDTO.class);
    }

}
