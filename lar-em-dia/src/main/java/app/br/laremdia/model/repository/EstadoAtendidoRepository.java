package app.br.laremdia.model.repository;

import app.br.laremdia.model.entity.EstadoAtendidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstadoAtendidoRepository extends JpaRepository<EstadoAtendidoEntity, Integer>{

    Optional<EstadoAtendidoEntity> findByUf(String uf);

    @Query(value = "select * from estado_atendido where estado_atendido.ativo = :ativo order by estado_atendido.uf asc;", nativeQuery = true)
    List<EstadoAtendidoEntity> ufAtivo(@Param("ativo") Boolean ativo);

    @Query(value = "SELECT * FROM estado_atendido ea INNER JOIN municipio_atendido ma ON  ea.id_estado = ma.id_municipio_id_estado \n" +
            "where  ( ea.ativo = true and ma.ativo = true and ea.id_estado = :id ) ORDER BY ma.municipio ASC;", nativeQuery = true)
    List<EstadoAtendidoEntity> ufAtivoPorUf(@Param("id") Integer id);


}
