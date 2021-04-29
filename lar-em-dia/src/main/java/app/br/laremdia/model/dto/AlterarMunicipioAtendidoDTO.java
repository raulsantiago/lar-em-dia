package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class AlterarMunicipioAtendidoDTO {

    private Boolean  ativo;
    private String   municipio;

    public static AlterarMunicipioAtendidoDTO create(MunicipioAtendidoEntity municipioAtendidoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(municipioAtendidoEntity, AlterarMunicipioAtendidoDTO.class);
    }
}
