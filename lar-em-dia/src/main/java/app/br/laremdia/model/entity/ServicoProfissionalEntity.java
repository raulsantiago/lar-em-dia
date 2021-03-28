package app.br.laremdia.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="servico_profissional")
public class ServicoProfissionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_servico")
    private Integer idServico;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = false)
    @NotNull(message = "{campo.ativo.obrigatorio}")
    private Boolean ativo;

    @OneToMany(mappedBy = "servicoProfissional", fetch = FetchType.LAZY)
    private List<TipoServicoEntity> tipoServicos;

}
