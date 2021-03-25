package app.br.laremdia.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String nome;

    @Column(nullable = true, length = 11)
    private String cpf;

    @Column(nullable = false, length = 70)
    private String email;

    @Column(nullable = true, length = 11)
    private String celular;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, length = 70)
    private String endereco;

    @Column(nullable = false, length = 15)
    private String numero;

    @Column(nullable = false, length = 50)
    private String bairro;

    @Column(nullable = true, length = 30)
    private String complemento;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 30)
    private String municipio;

    @Column(nullable = true)
    private String referencia;

    @Column(nullable = true)
    @Lob
    private byte[] foto;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(name="data_cadastro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
