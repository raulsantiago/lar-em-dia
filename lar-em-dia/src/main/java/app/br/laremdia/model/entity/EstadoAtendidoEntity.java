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
@Table(name="estado_atendido")
public class EstadoAtendidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_estado")
    private Integer idEstado;

    @Column(nullable = false, length = 2)
    @NotEmpty()
    private String uf;

    @Column(nullable = false)
    @NotNull()
    private Boolean ativo;

    @OneToMany(mappedBy = "estadoAtendido", fetch = FetchType.LAZY)
    private List<LoginClienteEntity> loginClientes;

    @OneToMany(mappedBy = "estado", fetch = FetchType.LAZY)
    private List<MunicipioAtendidoEntity> municipios;

}
