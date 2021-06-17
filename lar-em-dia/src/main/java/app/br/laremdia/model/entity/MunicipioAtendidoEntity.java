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
@Table(name="municipio_atendido")
public class MunicipioAtendidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_municipio")
    private Integer idMunicipio;

    @Column(nullable = false)
    @NotEmpty(message = "O campo município é obrigatório.")
    private String municipio;

    @Column(nullable = false)
    @NotNull(message = "O campo ativo é obrigatório.")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "id_municipio_id_estado")
    private EstadoAtendidoEntity estado;

    @OneToMany(mappedBy = "municipioAtendido", fetch = FetchType.LAZY)
    private List<LoginClienteEntity> loginClientes;

}
