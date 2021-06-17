package app.br.laremdia.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.*;

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

    @Column(nullable = false, length = 31)
    @NotEmpty(message = "O campo local é obrigatório.")
    private String local;

    @Column(name="data_hora_inicio", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataHoraInicio;

    @Column(name="data_hora_fim", nullable = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataHoraFim;

    @Column(nullable = true)
    @Digits(integer = 6, fraction = 2, message = "o preço está fora do limite esperado de <6 dígitos>.<2 dígitos>")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal despesas;

    @Column(nullable = false)
    @NotNull(message = "O campo ativo é obrigatório.")
    private Boolean situacao;

    @Column(name="preco_contratado", nullable = false)
    @Digits(integer = 6, fraction = 2, message = "o preço está fora do limite esperado de <6 dígitos>.<2 dígitos>")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal precoContratado;

    @OneToOne
    private AgendaEntity agenda;

    @ManyToOne
    @JoinColumn(name = "id_pedido_id_tipo")
    private TipoServicoEntity tipoServico;

    @ManyToOne
    @JoinColumn(name = "id_pedido_id_cliente")
    private LoginClienteEntity loginCliente;

}
