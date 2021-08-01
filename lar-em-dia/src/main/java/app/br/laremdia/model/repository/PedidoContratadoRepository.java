package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.PedidoContratadoEntity;
import app.br.laremdia.model.projection.PedidoContratadoGraficoProjection;
import app.br.laremdia.model.projection.PedidoContratadoProfissionalProjection;
import app.br.laremdia.model.projection.PedidoContratadoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoContratadoRepository extends JpaRepository< PedidoContratadoEntity, Integer> {

    @Query(value = "SELECT pc.id_pedido   AS idPedido, " +
                    " pc.data_hora_fim    AS dataFim, " +
                    " pc.preco_contratado AS precoContratado, " +
                    " ts.id_tipo          AS idTipoServico, " +
                    " ts.nome             AS nomeTipoServico, " +
                    " sp.id_servico       AS idServico, " +
                    " sp.nome             AS nomeServico, " +
                    " lc.id_cliente       AS idCliente, " +
                    " ag.id_agenda        AS idAgenda, " +
                    " ag.dia              AS dia, " +
                    " ag.turno            AS turno " +
    "FROM pedido_contratado pc " +
    "INNER JOIN tipo_servico ts         ON ts.id_tipo = pc.id_pedido_id_tipo " +
    "INNER JOIN agenda ag               ON ag.id_agenda = pc.agenda_id_agenda " +
    "INNER JOIN login_cliente lc        ON lc.id_cliente = pc.id_pedido_id_cliente " +
    "INNER JOIN servico_profissional sp ON sp.id_servico = ts.id_tipo_id_servico " +
    "WHERE ( lc.id_cliente = :idCliente ) ORDER BY ag.dia DESC, ag.turno ASC", nativeQuery = true)
    List< PedidoContratadoProjection > pedidosPorIdCliente(@Param("idCliente") Integer idCliente);

    @Query(value = "select dia from pedido_contratado pc " +
            " INNER JOIN agenda ag ON ag.id_agenda = pc.agenda_id_agenda " +
            " WHERE ( pc.id_pedido = :idPedido ) ", nativeQuery = true)
    LocalDate diaAgendado(@Param("idPedido") Integer idPedido);

    @Query(value = "SELECT pc.id_pedido   AS idPedido, " +
                    " pc.despesas         AS despesas, " +
                    " pc.descricao        AS descricao, " +
                    " pc.preco_contratado AS precoContratado, " +
                    " pc.local            AS local, " +
                    " ts.nome             AS nomeTipoServico, " +
                    " sp.nome             AS nomeServico, " +
                    " lc.id_cliente       AS idCliente, " +
                    " ag.dia              AS dia, " +
                    " ag.turno            AS turno " +
            "FROM pedido_contratado pc " +
            "INNER JOIN tipo_servico ts         ON ts.id_tipo = pc.id_pedido_id_tipo " +
            "INNER JOIN agenda ag               ON ag.id_agenda = pc.agenda_id_agenda " +
            "INNER JOIN login_cliente lc        ON lc.id_cliente = pc.id_pedido_id_cliente " +
            "INNER JOIN servico_profissional sp ON sp.id_servico = ts.id_tipo_id_servico " +
            "ORDER BY ag.dia DESC, ag.turno ASC", nativeQuery = true)
    List< PedidoContratadoProfissionalProjection > listaPedidosViewProfissional();

    @Query(value = "SELECT  despesas         AS despesas, " +
                           "preco_contratado AS precoContratado, " +
                           "data_hora_fim    AS dataFim " +
                   "FROM pedido_contratado " +
            "WHERE ( data_hora_fim BETWEEN :periodoInicial AND  :periodoFinal ) " +
            "ORDER BY data_hora_fim DESC", nativeQuery = true)
    List< PedidoContratadoGraficoProjection > listaPedidosGrafico(@Param("periodoInicial") LocalDateTime periodoInicial, @Param("periodoFinal") LocalDateTime periodoFinal);







}
