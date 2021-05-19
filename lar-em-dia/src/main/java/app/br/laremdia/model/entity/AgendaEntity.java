package app.br.laremdia.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name="agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_agenda")
    private Integer idAgenda;

    @Column(name="dia", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dia;

    @Column(nullable = false, length = 5)
    @NotEmpty()
    private String turno;

    @Column(nullable = false)
    @NotNull()
    private Boolean disponivel;

//    @OneToOne(mappedBy = "agenda")
//    private PedidoContratadoEntity pedidoContratado;

}
