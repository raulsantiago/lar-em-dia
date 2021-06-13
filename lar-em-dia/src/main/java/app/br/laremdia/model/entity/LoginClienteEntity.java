package app.br.laremdia.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="login_cliente")
public class LoginClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Integer idCliente;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = true, length = 11, unique=true)
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(nullable = false, length = 70, unique=true)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    @Column(nullable = true, length = 14)
    private String celular;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column(nullable = false, length = 70)
    @NotEmpty(message = "{campo.endereco.obrigatorio}")
    private String endereco;

    @Column(nullable = false, length = 15)
    @NotEmpty(message = "{campo.numero.obrigatorio}")
    private String numero;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.bairro.obrigatorio}")
    private String bairro;

    @Column(nullable = true, length = 30)
    private String complemento;

    @Column(nullable = true)
    private String referencia;

    @Column(nullable = true)
    private byte[] foto;

    @Column(nullable = false)
    @NotNull(message = "{campo.ativo.obrigatorio}")
    private Boolean ativo;

    @Column(name="data_cadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente_id_estado")
    private EstadoAtendidoEntity estadoAtendido;

    @ManyToOne
    @JoinColumn(name = "id_cliente_id_municipio")
    private MunicipioAtendidoEntity municipioAtendido;

    @OneToMany(mappedBy = "loginCliente", fetch = FetchType.LAZY)
    private List<PedidoContratadoEntity> pedidoContratados;

}
