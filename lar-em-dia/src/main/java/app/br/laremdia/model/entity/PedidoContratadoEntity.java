package app.br.laremdia.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name="pedido_contratado")
public class PedidoContratadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Integer idPedido;

    @Column(nullable = true)
    private String descricao;

    @Column(nullable = false, length = 28)
    @NotEmpty()
    private String local;

    @Column(name="data_hora_inicio", nullable = true)
    private ZonedDateTime dataHoraInicio;

    @Column(name="data_hora_fim", nullable = true)
    private ZonedDateTime dataHoraFim;

    @Column(nullable = true)
    @Digits(integer = 6, fraction = 2, message = "o preço está fora do limite esperado de <6 dígitos>.<2 dígitos>")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal despesas;

    @Column(nullable = false)
    @NotNull()
    private Boolean situacao;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "agenda_id_agenda", referencedColumnName = "id_agenda")
//    private AgendaEntity agenda;

    @OneToOne
    private AgendaEntity agenda;

    @ManyToOne
    @JoinColumn(name = "id_pedido_id_tipo")
    private TipoServicoEntity tipoServico;

    @ManyToOne
    @JoinColumn(name = "id_pedido_id_cliente")
    private LoginClienteEntity loginCliente;




}
