package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.ServicoProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoProfissionalRepository extends JpaRepository<ServicoProfissionalEntity, Integer> {

    Optional<ServicoProfissionalEntity> findByNome(String nome);

    List< ServicoProfissionalEntity > findAllByAtivoOrderByNomeAsc(Boolean Ativo);
}
