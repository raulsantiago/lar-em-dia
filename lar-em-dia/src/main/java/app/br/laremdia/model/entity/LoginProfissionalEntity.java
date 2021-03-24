package app.br.laremdia.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Data
@NoArgsConstructor
@Table(name="login_profissional")
public class LoginProfissionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_profissional")
    private Integer idProfissional;

    @Column(nullable = false, length = 50)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(nullable = true, length = 11)
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    @Column(nullable = false, length = 70)
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;

    @Column(nullable = true, length = 11)
    private String celular;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.senha.obrigatorio}")
    private String senha;

    @Column(nullable = true)
    @Lob
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

}
