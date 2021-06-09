package app.br.laremdia.model.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PedidoContratadoProfissionalProjection {

    // Pedido contratado
    public Integer       getIdPedido();
    public BigDecimal    getDespesas();
    public String        getDescricao();
    public String        getLocal();
    public BigDecimal    getPrecoContratado();

    // Tipo serviço
    public String        getNomeTipoServico();

    // Serviço profissional
    public String        getNomeServico();

    // Cliente
    public Integer       getIdCliente();

    // Agenda
    public LocalDate     getDia();
    public String        getTurno();


}
