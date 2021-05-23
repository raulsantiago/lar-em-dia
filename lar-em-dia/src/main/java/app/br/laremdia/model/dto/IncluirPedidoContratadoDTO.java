package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.PedidoContratadoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;

@Data
public class IncluirPedidoContratadoDTO {

    private String     descricao;
    private String     local;
    private Boolean    situacao;
    private BigDecimal precoContratado;
    private Integer    idAgenda;
    private Integer    idTipo;
    private Integer    idCliente;

    public static IncluirPedidoContratadoDTO create(PedidoContratadoEntity pedidoContratadoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pedidoContratadoEntity, IncluirPedidoContratadoDTO.class);
    }

}
