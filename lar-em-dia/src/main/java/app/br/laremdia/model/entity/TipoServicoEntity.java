package app.br.laremdia.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name="tipo_servico")
public class TipoServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo")
    private Integer idTipo;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false)
    @Digits(integer = 6, fraction = 2, message = "valor numérico fora do limite esperado de <6 dígitos>.<2 dígitos>")
    @DecimalMin(value = "0.0", inclusive = false)
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_tipo_id_servico")
    private ServicoProfissionalEntity servicoProfissional;

}
