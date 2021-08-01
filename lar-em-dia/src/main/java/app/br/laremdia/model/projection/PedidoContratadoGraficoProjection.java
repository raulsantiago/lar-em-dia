package app.br.laremdia.model.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PedidoContratadoGraficoProjection {

    public BigDecimal    getDespesas();
    public BigDecimal    getPrecoContratado();
    public LocalDateTime getDataFim();

}
