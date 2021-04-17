package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.TipoServicoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;

@Data
public class IncluirTipoServicoDTO {

    private String      nome;
    private BigDecimal  preco;
    private Integer     idServico;

    public static IncluirTipoServicoDTO create(TipoServicoEntity tipoServicoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tipoServicoEntity, IncluirTipoServicoDTO.class);
    }

}