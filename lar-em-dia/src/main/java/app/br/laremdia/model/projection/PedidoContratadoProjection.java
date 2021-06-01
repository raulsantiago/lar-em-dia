package app.br.laremdia.model.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PedidoContratadoProjection {

    // Pedido contratado
    public Integer       getIdPedido();
    public LocalDateTime getDataFim();
    public BigDecimal    getPrecoContratado();

    // Tipo serviço
    public Integer       getIdTipoServico();
    public String        getNomeTipoServico();

    // Serviço profissional
    public Integer       getIdServico();
    public String        getNomeServico();

    // Cliente
    public Integer       getIdCliente();

    // Agenda
    public Integer       getIdAgenda();
    public LocalDate     getDia();
    public String        getTurno();

}
