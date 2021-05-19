package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository< AgendaEntity, Integer> {

}
