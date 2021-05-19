package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.AgendaEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgendaRepository extends JpaRepository< AgendaEntity, Integer> {

    List< AgendaEntity > findAllByDisponivelOrderByDiaAscTurnoAsc(Boolean Disponivel);
}
