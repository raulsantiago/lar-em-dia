package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.TipoServicoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;

@Data
public class AlterarTipoServicoDTO {

    private String     nome;
    private BigDecimal preco;

    public static AlterarTipoServicoDTO create(TipoServicoEntity tipoServicoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tipoServicoEntity, AlterarTipoServicoDTO.class);
    }

}
