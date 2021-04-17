package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.TipoServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoServicoRepository extends JpaRepository<TipoServicoEntity, Integer> {

    @Query(value = "SELECT * FROM tipo_servico INNER JOIN servico_profissional ON tipo_servico.id_tipo_id_servico = servico_profissional.id_servico "
            + "ORDER BY servico_profissional.nome ASC, tipo_servico.nome ASC", nativeQuery=true)
    List<TipoServicoEntity> listar();

}
