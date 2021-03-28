package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.TipoServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoServicoRepository extends JpaRepository<TipoServicoEntity, Integer> {
}
