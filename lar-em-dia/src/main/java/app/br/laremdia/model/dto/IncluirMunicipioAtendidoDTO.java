package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.MunicipioAtendidoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class IncluirMunicipioAtendidoDTO {

    private String   municipio;
    private Boolean  ativo;
    private String   uf;

    public static IncluirMunicipioAtendidoDTO create(MunicipioAtendidoEntity municipioAtendidoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(municipioAtendidoEntity, IncluirMunicipioAtendidoDTO.class);
    }

}
