package app.br.laremdia.model.dto;

import app.br.laremdia.model.entity.PedidoContratadoEntity;
import lombok.Data;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AlterarPedidoContratadoDTO {

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private BigDecimal    despesas;
    private Boolean       situacao;

    public static AlterarPedidoContratadoDTO create(PedidoContratadoEntity pedidoContratadoEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(pedidoContratadoEntity, AlterarPedidoContratadoDTO.class);
    }

}
